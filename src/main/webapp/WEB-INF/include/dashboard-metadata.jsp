<div class="dashboard">
  <form id="dashboard-metadata" method="post" action="./_dashboard">
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr>
        <td align="center" style="padding: 20px">
          <h1>Show Metadata</h1>
        </td>
      </tr>
    </table>

    <c:forEach var="table" items="${metadata}">
      <table class="table table-striped">
        <thead>
          <tr>
            <c:forEach var="col" items="${table.head}">
              <th>${col}</th>
            </c:forEach>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="row" items="${table.row}">
            <tr>
              <c:forEach var="col" items="${row.col}">
                <td>${col}</td>
              </c:forEach>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:forEach>

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr class="title">
        <td align="center" style="padding: 20px">
            <button type="submit" class="btn btn-default delete big" formmethod="post" formaction="./_dashboard">Return to Dashboard</button>
        </td>
      </tr>
    </table>
  </form>
</div>