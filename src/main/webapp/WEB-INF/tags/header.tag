<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value='${sessionScope["user"]}' var="user"/>
<%@ attribute name="title" required="true" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/styles/styles.css">
    <title>${title}</title>
</head>
<header>
    <div>
        <c:choose>
            <c:when test="${user.getRole() == 'admin'}">
                <c:if test="${user.getRole() == 'admin'}">
                    <label class="left-link" style="margin: 20px">
                        <a href="${pageContext.servletContext.contextPath}/users">
                            Admin Panel
                        </a>
                    </label>
                </c:if>
            </c:when>
            <c:otherwise>
                <label>
                    <a href="${pageContext.servletContext.contextPath}/films">
                        Film Ratings
                    </a>
                </label>
            </c:otherwise>
        </c:choose>
            <div style="float: right">
                <label><c:out value="${user.getName()}"/></label>
                <form action="${pageContext.servletContext.contextPath}/auth/logout" method="POST">
                    <button type="submit">
                        Logout
                    </button>
                </form>
            </div>
    </div>

</header>
<jsp:doBody />
</html>