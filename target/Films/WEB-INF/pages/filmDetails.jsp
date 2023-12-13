<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value='${sessionScope["user"]}' var="user"/>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:header title="${film.getName()}"/>

<body>
<div class="wrapper">
    <main>
        <a href="${pageContext.servletContext.contextPath}/films"><</a>
        <div>
            <div>
                <img src="${film.getImg()}" alt="${film.getName()}" class="poster">
                <div>
                    <c:choose>
                        <c:when test="${reviews.size() > 0 && reviews.get(0).getUserId() == user.getId()}">
                            <button disabled="disabled">
                                <span>+</span>
                                Добавить отзыв
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="openReviewModal()">
                                <span>+</span>
                                Добавить отзыв
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div>
                <div>
                    ${film.getName()}
                    <span>${film.getRating()}</span>
                </div>
                <div>${film.getDescription()}</div>
                <div>
                    <h2>Отзывы</h2>
                    <c:forEach var="review" items="${reviews}">
                        <div>
                            <div>
                                <p>${review.getUserName()}</p>
                                <p  ${review.getUserRole() == "admin" ? "cyan" : review.getUserRating() >= 0 ? "green" : "red"}">
                                        ${review.getUserRole() == "admin" ?
                                        "ADMIN" :
                                        (review.getUserRating() > 0 ? '+' : '').concat(review.getUserRating())}
                                </p>
                                <p>${review.getRating()}</p>
                                <c:if test="${user.getName() == review.getUserName()}">
                                    <form action="${pageContext.servletContext.contextPath}/films/review/delete"
                                          method="post" style="margin-left: auto">
                                        <input type="hidden" name="filmId" value="${film.getId()}">
                                        <input type="hidden" name="reviewId" value="${review.getId()}">
                                        <button>Удалить</button>
                                    </form>
                                </c:if>

                            </div>
                            <p class="text">${review.getText()}</p>
                        </div>
                    </c:forEach>
                </div>
                <div class="modal-wrapper" id="reviewModal">
                    <div>
                        <h2>Добавить отзыв</h2>
                        <form action="${pageContext.servletContext.contextPath}/films/review/add" method="post">
                            <input type="hidden" name="filmId" value="${film.getId()}">
                            <label for="userRating">Оценка:</label>
                            <input
                                    id="userRating"
                                    type="number"
                                    name="rating"
                                    value="10"
                                    min="1"
                                    max="10"
                                    step="0.1"
                                    required
                            >
                            <label for="reviewText">Отзыв:</label>
                            <textarea name="text" id="reviewText" required></textarea>
                            <button type="submit">Добавить отзыв</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<script>
    openReviewModal = () => {
        const modal = document.getElementById("reviewModal");
        modal.style.display = "flex";
    }

    window.onclick = (event) => {
        const modal = document.getElementById("reviewModal");
        if (event.target === modal) {
            const userRatingInput = document.getElementById("userRating");
            const reviewTextInput = document.getElementById("reviewText");
            userRatingInput.value = 10;
            reviewTextInput.value = '';
            modal.style.display = "none";
        }
    }
</script>
</body>