<div class="dashboard">
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr>
        <td align="center" style="padding: 20px">
          <h1>Show Metadata</h1>
        </td>
      </tr>
    </table>

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr>
        <td align="center" style="padding: 20px">
          <table class="table table-striped" align="center" border="0" cellpadding="0" cellspacing="0">
            <thead>
              <tr>
                  <th>${metadataTblsHead}</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="tableName" items="${metadataTbls}">
                <tr>
                    <td>${tableName}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <c:forEach var="table" items="${metadata}">
            <table class="table table-striped" align="center" border="0" cellpadding="0" cellspacing="0">
              <thead>
                <tr>
                    <th colspan="2">${table.key}</th>
                </tr>
              </thead>
              <tbody>
                  <tr>
                    <th>${metadataAttribute}</th>
                    <th>${metadataType}</th>
                  </tr>
                <c:forEach var="row" items="${table.value}">
                  <tr>
                      <td>${row.key}</td>
                      <td>${row.value}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:forEach>
        </td>
      </tr>
    </table>

  <form id="dashboard-metadata" method="post" action="./_dashboard">
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
      <tr class="title">
        <td align="center" style="padding: 20px">
            <button type="submit" class="btn btn-default delete big" formmethod="post" formaction="./_dashboard">Return to Dashboard</button>
        </td>
      </tr>
    </table>
  </form>
</div>