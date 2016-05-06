<div class="dashboard">
  <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
    <tr>
      <td align="center" style="padding: 20px">
        <h1>Employee Login</h1>
      </td>
    </tr>
  </table>

  <form id="dashboard-login" method="post" action="./_dashboard">

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
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Email</span>
            <input type="text" class="form-control" name="email" value="${param.email}">
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Password</span>
            <input type="text" class="form-control" name="password">
          </div>
        </td>
      </tr>
    </table>
    <!-- End of Dashboard Login -->

    <!-- Start of 'Sign In' Button -->
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr class="title">
        <td align="center" style="padding: 20px">
            <button type="submit" class="btn btn-default update big" name="action" value="login">Sign In</button>
        </td>
      </tr>
    </table>
    <!-- End of 'Sign In' Button -->
  </form>
</div>