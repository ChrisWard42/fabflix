<div class="dashboard">
  <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
    <tr>
      <td align="center" style="padding: 20px">
        <h1>Update Movie</h1>
      </td>
    </tr>
  </table>

  <form id="dashboard-updatemovie" method = "post" action="./_dashboard">

    <!-- Start of Dashboard Login -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr>
        <td>
          <c:if test="${empty errorMsg == false}">
            <div class="alert alert-danger" role="alert">${errorMsg}</div>
          </c:if>
        </td>
      </tr>

      <tr>
        <td style="text-align: center">Please provide the information for the movie you wish to update.</td>
      </tr>

      <tr>
        <td align="left" style="padding: 10px">
          <h3>Movie Details</h3>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Title</span>
            <input type="text" class="form-control" name="movieTitle" value="${param.movieTitle}" placeholder="Required">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Year</span>
            <input type="text" class="form-control" name="movieYear" value="${param.movieYear}" placeholder="Required">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Director</span>
            <input type="text" class="form-control" name="movieDirector" value="${param.movieDirector}" placeholder="Required">
          </div>
        </td>
      </tr>
    </table>
    <!-- End of Dashboard Login -->

    <!-- Start of 'Sign In' Button -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr class="title">
        <td align="center" width="50%" style="padding: 20px">
            <button type="submit" class="btn btn-default delete big" formmethod="post" formaction="./_dashboard">Return to Dashboard</button>
        </td>
        <td align="center" width="50%" style="padding: 20px">
            <button type="submit" class="btn btn-default update big" name="action" value="updatemoviecheck">Submit</button>
        </td>
      </tr>
    </table>
    <!-- End of 'Sign In' Button -->
  </form>
</div>