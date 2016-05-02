<!-- Start of Dashboard Index -->
<div class="dashboard">
    <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
        <tr>
            <td align="center" style="padding: 20px">
                <h3 style="text-align: center">Dashboard</h3><br>

                <p style="text-align: center">Welcome, ${employee.fullname}.<br>
                Please select an action to perform.
                </p><br>

                <c:if test="${empty errorMsg == false}">
                    <div class="alert alert-danger" role="alert">${errorMsg}</div><br>
                    <c:remove var="errorMsg" scope="session" />
                </c:if>
                <c:if test="${empty successMsg == false}">
                    <div class="alert alert-success" role="alert">${successMsg}</div><br>
                    <c:remove var="successMsg" scope="session" />
                </c:if>

                <a href="./_dashboard/insertmovie" class="btn btn-default btn-lg" role="button">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Insert New Movie&nbsp;&nbsp;&nbsp;&nbsp;</a><br>
                <a href="./_dashboard/updatemovie" class="btn btn-default btn-lg" role="button">Update Existing Movie</a><br>
                <a href="./_dashboard/insertstar" class="btn btn-default btn-lg" role="button">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Insert New Star&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><br>
                <a href="./_dashboard/metadata" class="btn btn-default btn-lg" role="button">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Show Metadata&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </td>
        </tr>
    </table>
</div>
<!-- End of Dashboard Index -->