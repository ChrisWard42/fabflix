$(function() {
    // Autocomplete Feature
    $("#global-search").autocomplete({
        source: "./ajax/autocomplete",
        minLength: 1,
        select: function(event, ui) {
            window.location.assign("./movie/" + ui.item.id);
        }
    });

    // Tooltip Feature
    $(document).uitooltip({
        items: "#movie-title",
        tooltipClass: 'preview-tip',
        track: true,
        content: function(callback) {
            var element = $(this);
            if (element.is("#movie-title")) {
                var id = element.attr("href").replace("./movie/", "");
                $.getJSON("./ajax/tooltip?id=" + id, function(json) {
                    var html_output = "<div class='movie-tooltip-image'>";
                    // Add Banner
                    if (json.bannerUrl !== 'undefined' && json.bannerUrl !== '')
                        html_output += "<img src='" + json.bannerUrl + "' style='max-height: 100px; max-width: 100px; margin: auto;' /><br>";
                    else
                        html_output += "[No Banner Available]<br>";
                    // Add Genres
                    html_output += "</div><div class='movie-tooltip-text'>";
                    html_output += "<b>Genres:</b> ";
                    json.genreSet.forEach(function(genre) {
                        html_output += genre + ", ";
                    });
                    html_output = html_output.slice(0, -2) + "<br>";
                    // Add Year
                    if (json.year !== 'undefined' && json.year !== '')
                        html_output += "<b>Year:</b> " + json.year + "<br>";
                    else
                        html_output += "<b>Year:</b> N/A<br>";
                    // Add Director
                    if (json.director !== 'undefined' && json.director !== '')
                        html_output += "<b>Director:</b> " + json.director + "<br>";
                    else
                        html_output += "<b>Director:</b> N/A<br>";
                    // Add Stars
                    html_output += "<b>Stars:</b> ";
                    json.starSet.forEach(function(star) {
                        html_output += star.firstName + " " + star.lastName + ", ";
                    });
                    html_output = html_output.slice(0, -2) + "<br>";
                    // Add Trailer URL
                    if (json.trailerUrl !== 'undefined' && json.trailerUrl !== '')
                        html_output += "<b>Trailer:</b> " + json.trailerUrl;
                    else
                        html_output += "<b>Trailer:</b> N/A";
                    html_output += "</div>";

                    console.log(html_output);
                    callback(html_output);
                });
            }
        }
    });
});