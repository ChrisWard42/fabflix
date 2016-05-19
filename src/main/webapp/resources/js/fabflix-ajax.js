// Autocomplete Feature
$(function() {
    $("#global-search").autocomplete({
        source: "./ajax/autocomplete",
        minLength: 2
    });
});

// Tooltip Feature