<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value='${sessionScope["user"]}' var="user"/>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:header title="Films"/>

<body>
<div style="margin-top: 10px">
    <main>
        <div>
            <c:if test="${user.getRole() == 'admin'}">
                <button class="add" onclick="openAddFilmModel()">
                    <span>+</span>
                    Добавить новый фильм
                </button>
            </c:if>
        </div>
        <div>
            <c:forEach var="film" items="${films}">
                <div>
                    <a href="${pageContext.servletContext.contextPath}/films/${film.getId()}">
                        <img src="${film.getImg()}" alt="${film.getName()}" class="poster">
                        <div>${film.getRating()}</div>
                        <div>${film.getName()}</div>
                    </a>
                    <c:if test="${user.getRole() == 'admin'}">
                        <button
                                onclick="openEditFilmModel(`${film.getId()}`, `${film.getName()}`, `${film.getDescription()}`, `${film.getImg()}`)">
                            Редактировать фильм
                        </button>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div class="modal-wrapper" id="addFilmModal">
            <div>
                <h2>Добавить новый фильм</h2>
                <form action="${pageContext.servletContext.contextPath}/films/add" method="post">
                    <label for="addFilmTitle">Название:</label>
                    <input type="text" name="title" id="addFilmTitle" required>

                    <label for="addFilmDescription">Описание:</label>
                    <textarea name="description" id="addFilmDescription" required></textarea>

                    <label for="addFilmImgUrl">URL превью:</label>
                    <input type="text" name="imgUrl" id="addFilmImgUrl" required>

                    <button type="submit">Добавить</button>
                </form>
            </div>
        </div>
        <div class="modal-wrapper" id="editFilmModal">
            <div>
                <h2>Редактировать фильм</h2>
                <form action="${pageContext.servletContext.contextPath}/films/edit" method="post">
                    <input type="hidden" name="filmId" id="editFilmId" value="">
                    <label for="editFilmTitle">Название:</label>
                    <input type="text" name="title" id="editFilmTitle" required>

                    <label for="editFilmDescription">Описание:</label>
                    <textarea name="description" id="editFilmDescription" required></textarea>

                    <label for="editFilmImgUrl">URL превью:</label>
                    <input type="text" name="imgUrl" id="editFilmImgUrl" required>

                    <button type="submit">Подтвердить</button>
                </form>
            </div>
        </div>
    </main>
</div>
<script>
    openEditFilmModel = (filmId, filmTitle, filmDescription, imgUrl) => {
        const model = document.getElementById("editFilmModal");
        model.style.display = "flex";
        document.getElementById("editFilmId").value = filmId;
        document.getElementById("editFilmTitle").value = filmTitle;
        document.getElementById("editFilmDescription").value = filmDescription;
        document.getElementById("editFilmImgUrl").value = imgUrl;
    }

    openAddFilmModel = () => {
        const modal = document.getElementById("addFilmModal");
        modal.style.display = "flex";
    }

    window.onclick = (event) => {
        const addModel = document.getElementById("addFilmModal");
        const editModel = document.getElementById("editFilmModal");
        if (event.target === addModel) {
            addModel.style.display = "none";
        }
        if (event.target === editModel) {
            editModel.style.display = "none";
        }
    }
</script>
</body>
