<div class="dashboard">
  <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
    <tr>
      <td align="center" style="padding: 20px">
        <h1>Insert Movie</h1>
      </td>
    </tr>
  </table>

  <form id="dashboard-insertmovie" method = "post" action="./_dashboard">
    <input type="hidden" name="movieTitle" value="${movieTitle}">
    <input type="hidden" name="movieYear" value="${movieYear}">
    <input type="hidden" name="movieDirector" value="${movieDirector}">
    <input type="hidden" name="movieBannerUrl" value="${movieBannerUrl}">
    <input type="hidden" name="movieTrailerUrl" value="${movieTrailerUrl}">

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
        <td style="text-align: center">Please provide the information to update for the following movie:<br>
        <b>Title:</b> ${movieTitle}<br>
        <b>Year:</b> ${movieYear}<br>
        <b>Director:</b> ${movieDirector}</td>
      </tr>

      <tr>
        <td align="left" style="padding: 10px">
          <h3>Genre Details (Optional)</h3>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Name</span>
            <input type="text" class="form-control" name="genreName" value="${param.genreName}" placeholder="Required">
          </div>
        </td>
      </tr>

      <tr>
        <td align="left" style="padding: 10px">
          <h3>Star Details (Optional)</h3>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">First Name</span>
            <input type="text" class="form-control" name="starFirstName" value="${param.starFirstName}" placeholder="Required">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Last Name</span>
            <input type="text" class="form-control" name="starLastName" value="${param.starLastName}" placeholder="Required">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Date of Birth</span>
            <input type="text" class="form-control" name="starDob" value="${param.starDob}" placeholder="Optional">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Photo URL</span>
            <input type="text" class="form-control" name="starPhotoUrl" value="${param.starPhotoUrl}" placeholder="Optional">
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
            <button type="submit" class="btn btn-default update big" name="action" value="updatemovie">Submit</button>
        </td>
      </tr>
    </table>
    <!-- End of 'Sign In' Button -->
  </form>
</div>