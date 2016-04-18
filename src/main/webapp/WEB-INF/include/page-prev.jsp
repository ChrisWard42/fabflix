<c:url var="pagePrevUrl" value="/search">
    <c:if test="${empty param.query == false}">
        <c:param name="query" value="${param.query}" />
    </c:if>
    <c:if test="${empty param.title == false}">
        <c:param name="title" value="${param.title}" />
    </c:if>
    <c:if test="${empty param.year == false}">
        <c:param name="year" value="${param.year}" />
    </c:if>
    <c:if test="${empty param.director == false}">
        <c:param name="director" value="${param.director}" />
    </c:if>
    <c:if test="${empty param.star == false}">
        <c:param name="star" value="${param.star}" />
    </c:if>
    <c:if test="${empty param.genre == false}">
        <input type="hidden" name="genre" value="${param.genre}">
    </c:if>
    <c:if test="${empty param.startsWith == false}">
        <input type="hidden" name="startsWith" value="${param.startsWith}">
    </c:if>
    <c:choose>
        <c:when test="${empty param.limit == false}">
            <c:param name="limit" value="${param.limit}" />
        </c:when>
        <c:otherwise>
            <c:param name="limit" value="10" />
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${empty param.sort == false}">
            <c:param name="sort" value="${param.sort}" />
        </c:when>
        <c:otherwise>
            <c:param name="sort" value="title-asc" />
        </c:otherwise>
    </c:choose>
    <c:param name="page" value="${param.page - 1}" />
</c:url>