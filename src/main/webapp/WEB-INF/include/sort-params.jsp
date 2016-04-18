<!-- Define hidden inputs for buttons -->
<c:if test="${empty param.query == false}">
    <input type="hidden" name="query" value="${param.query}">
</c:if>
<c:if test="${empty param.title == false}">
    <input type="hidden" name="title" value="${param.title}">
</c:if>
<c:if test="${empty param.year == false}">
    <input type="hidden" name="year" value="${param.year}">
</c:if>
<c:if test="${empty param.director == false}">
    <input type="hidden" name="director" value="${param.director}">
</c:if>
<c:if test="${empty param.star == false}">
    <input type="hidden" name="star" value="${param.star}">
</c:if>
<c:if test="${empty param.genre == false}">
    <input type="hidden" name="genre" value="${param.genre}">
</c:if>
<c:if test="${empty param.startsWith == false}">
    <input type="hidden" name="startsWith" value="${param.startsWith}">
</c:if>
<c:choose>
    <c:when test="${empty param.limit == false}">
        <input type="hidden" name="limit" value="${param.limit}">
    </c:when>
    <c:otherwise>
        <input type="hidden" name="limit" value="10">
    </c:otherwise>
</c:choose>
<input type="hidden" name="page" value="1">
<!-- End of hidden input definition -->