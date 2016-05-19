<div class="movie_list">
  <%@ include file="page-prev.jsp" %>
  <%@ include file="page-next.jsp" %>
  <table border="0" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td align="left">
        <c:if test="${pageContext.request.servletPath == '/WEB-INF/search.jsp'}">
          <form id="sort" method="get" action="./search">
        </c:if>
        <c:if test="${pageContext.request.servletPath == '/WEB-INF/browse.jsp'}">
          <form id="sort" method="get" action="./browse">
        </c:if>
        <div class="input-group">
          <span class="input-group-addon" id="basic-addon1">Sort by:</span>
          <div class="input-group-btn">
              <%@ include file="sort-params.jsp" %>
              <c:choose>
                <c:when test="${param.sort == 'title-asc'}">
                  <button class="btn btn-default" type="submit" name="sort" value="title-desc"><img src="resources/img/triangle_up.png" alt="" height="10px" hspace="10px">Title</button>
                </c:when>
                <c:when test="${param.sort == 'title-desc'}">
                  <button class="btn btn-default" type="submit" name="sort" value="title-asc"><img src="resources/img/triangle_down.png" alt="" height="10px" hspace="10px">Title</button>
                </c:when>
                <c:otherwise>
                  <button class="btn btn-default" type="submit" name="sort" value="title-asc"><img src="resources/img/triangle_none.png" alt="" height="10px" hspace="10px">Title</button>
                </c:otherwise>
              </c:choose>

              <c:choose>
                <c:when test="${param.sort == 'year-asc'}">
                  <button class="btn btn-default" type="submit" name="sort" value="year-desc"><img src="resources/img/triangle_up.png" alt="" height="10px" hspace="10px">Year</button>
                </c:when>
                <c:when test="${param.sort == 'year-desc'}">
                  <button class="btn btn-default" type="submit" name="sort" value="year-asc"><img src="resources/img/triangle_down.png" alt="" height="10px" hspace="10px">Year</button>
                </c:when>
                <c:otherwise>
                  <button class="btn btn-default" type="submit" name="sort" value="year-asc"><img src="resources/img/triangle_none.png" alt="" height="10px" hspace="10px">Year</button>
                </c:otherwise>
              </c:choose>
          </div>
        </div>
        </form>
      </td>
      <td align="right">
        <c:if test="${pageContext.request.servletPath == '/WEB-INF/search.jsp'}">
          <form id="limit" method="get" action="./search">
        </c:if>
        <c:if test="${pageContext.request.servletPath == '/WEB-INF/browse.jsp'}">
          <form id="limit" method="get" action="./browse">
        </c:if>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Items per Page:</span>
            <div class="input-group-btn">
                <%@ include file="limit-params.jsp" %>
                <c:if test="${param.limit != 10 and empty param.limit != true}">
                  <button class="btn btn-default" type="submit" name="limit" value="10">10</button>
                </c:if>
                <c:if test="${param.limit == 10 or empty param.limit}">
                  <button class="btn btn-default active" type="submit" aria-pressed="true" name="limit" value="10">10</button>
                </c:if>
                <c:if test="${param.limit != 25}">
                  <button class="btn btn-default" type="submit" name="limit" value="25">25</button>
                </c:if>
                <c:if test="${param.limit == 25}">
                  <button class="btn btn-default active" type="submit" aria-pressed="true" name="limit" value="25">25</button>
                </c:if>
                <c:if test="${param.limit != 50}">
                  <button class="btn btn-default" type="submit" name="limit" value="50">50</button>
                </c:if>
                <c:if test="${param.limit == 50}">
                  <button class="btn btn-default active" type="submit" aria-pressed="true" name="limit" value="50">50</button>
                </c:if>
            </div>
          </div>
        </form>
      </td>
    </tr>
  </table>

  <!-- Start on top pagination -->
  <nav>
    <ul class="pager">
      <c:choose>
        <c:when test="${empty param.page or param.page == 1}">
          <li class="previous"><span aria-hidden="true">&larr; Previous</span></li>
        </c:when>
        <c:otherwise>
          <li class="previous"><a href="${pagePrevUrl}"><span aria-hidden="true">&larr;</span> Previous</a></li>
        </c:otherwise>
      </c:choose>
      <c:choose>
        <c:when test="${param.page >= maxPage}">
          <li class="next"><span aria-hidden="true">Next &rarr;</span></li>
        </c:when>
        <c:otherwise>
          <li class="next"><a href="${pageNextUrl}">Next <span aria-hidden="true">&rarr;</span></a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </nav>
  <!-- End on top pagination -->

  <table class="list" align="center" border="0" cellpadding="0" cellspacing="0" width="80%">
    <c:forEach var="item" items="${movieDisplay}">
      <tr style="padding: 10px 0" height="200px">
        <td class="poster_pic">
          <a href="./movie/${item.id}">
          <img src="${item.bannerUrl}" crossorigin="anonymous" alt="" width="100%" onError="this.src='resources/img/dora-error-img.png';" />
          </a>
        </td>

        <td class="spacing"></td>

        <td class="description">
          <h3><a id="movie-title" href="./movie/${item.id}" data-id="${item.id}">${item.title}</a> (${item.year})</h3>
          <h4 id="genres"><c:forEach var="genre" items="${item.genreSet}" varStatus="genreSetStatus">
                      <c:choose>
                        <c:when test="${genreSetStatus.last == true}">
                          ${genre}
                        </c:when>
                        <c:otherwise>
                          ${genre},&nbsp;
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
          </h4>
          <h4 id="id">[${item.id}]</h4>
          <br>
          <h4>
            Director:&nbsp;${item.director}
            <br>
            Stars:&nbsp;<c:forEach var="star" items="${item.starSet}" varStatus="starSetStatus">
                      <c:choose>
                        <c:when test="${starSetStatus.last == true}">
                          <a href="./star/${star.id}">${star.firstName}&nbsp;${star.lastName}</a>
                        </c:when>
                        <c:otherwise>
                          <a href="./star/${star.id}">${star.firstName}&nbsp;${star.lastName}</a>,&nbsp;
                        </c:otherwise>
                      </c:choose>
                   </c:forEach>
            <br>
          </h4>

          <table class="table_buttons" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td>
                <form id="cart" method="post" action="./cart">
                  <input type="hidden" name="action" value="add">
                  <button type="submit" class="btn btn-default" name="productId" value="${item.id}"><img src="resources/img/shop.png" alt="cart" height="20px"> Add to Cart</button>
                </form>
              </td>

              <td class="spacing"></td>

              <td>
                <form id="trailer" method="get" action="${item.trailerUrl}">
                  <button type="submit" class="btn btn-default"><img src="resources/img/watch.png" alt="watch" height="20px"> Watch Trailer</button>
                </form>
              </td>

              <td class="spacing"></td>

              <td>
                <form id="banner" method="get" action="${item.bannerUrl}">
                  <button type="submit" class="btn btn-default"><img src="resources/img/view.png" alt="view" height="20px"> View Banner</button>
                </form>
              </td>
            </tr>
          </table>

        </td>
      </tr>
    </c:forEach>
  </table>

  <!-- Start on bottom pagination -->
  <nav>
    <ul class="pager">
      <c:choose>
        <c:when test="${empty param.page or param.page == 1}">
          <li class="previous"><span aria-hidden="true">&larr; Previous</span></li>
        </c:when>
        <c:otherwise>
          <li class="previous"><a href="${pagePrevUrl}"><span aria-hidden="true">&larr;</span> Previous</a></li>
        </c:otherwise>
      </c:choose>
      <c:choose>
        <c:when test="${param.page >= maxPage}">
          <li class="next"><span aria-hidden="true">Next &rarr;</span></li>
        </c:when>
        <c:otherwise>
          <li class="next"><a href="${pageNextUrl}">Next <span aria-hidden="true">&rarr;</span></a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </nav>
  <!-- End on bottom pagination -->
</div>