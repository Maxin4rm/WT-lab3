<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value='${sessionScope["user"]}' var="user"/>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:header title="Admin Panel"/>
<body>
<div>
    <main>
        <a href="${pageContext.servletContext.contextPath}/films"><</a>
        <table>
            <thead>
            <tr>
                <th>Имя</th>
                <th>Почта</th>
                <th>Оценка</th>
                <th>Заблокировать/Разблокировать</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userInfo" items="${users}">
                <tr>
                    <td>${userInfo.getName()}</td>
                    <td>${userInfo.getEmail()}</td>
                    <td>
                        <div>
                            <form action="${pageContext.servletContext.contextPath}/users/${userInfo.getId()}" method="POST">
                                <input type="hidden" name="id" value="${userInfo.getId()}">
                                <input type="hidden" name="rating" value="${userInfo.getRating() - 1}">
                                <input type="hidden" name="isBanned" value="${userInfo.isBanned()}">
                                <button type="submit">-</button>
                            </form>
                                ${userInfo.getRating()}
                            <form action="${pageContext.servletContext.contextPath}/users/${userInfo.getId()}" method="POST">
                                <input type="hidden" name="id" value="${userInfo.getId()}">
                                <input type="hidden" name="rating" value="${userInfo.getRating() + 1}">
                                <input type="hidden" name="isBanned" value="${userInfo.isBanned()}">
                                <button type="submit">+</button>
                            </form>
                        </div>
                    </td>
                    <td style="width: 10%; text-align: center">
                        <form action="${pageContext.servletContext.contextPath}/users/${userInfo.getId()}" method="POST">
                            <input type="hidden" name="id" value="${userInfo.getId()}">
                            <input type="hidden" name="rating" value="${userInfo.getRating()}">
                            <input type="hidden" name="isBanned" value="${!userInfo.isBanned()}">
                            <button type="submit">${userInfo.isBanned() ? "Разблокировать" : "Заблокировать"}</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
</div>
</body>