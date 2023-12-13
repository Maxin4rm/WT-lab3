<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/styles.css">
</head>
<body>
<div>
    <form action="${pageContext.servletContext.contextPath}/auth/signup" method="post" class="form">
        <h1>Регистрация</h1>
        <div>
            <label for="name">Имя пользователя:</label>
            <input type="text" id="name" name="name" required/>
        </div>
        <div>
            <label for="email">Почта:</label>
            <input type="email" id="email" name="email" required/>
        </div>
        <div>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required/>
        </div>
        <div>
            <label for="re_password">Подтвердите пароль:</label>
            <input type="password" id="re_password" name="re_password" required/>
        </div>
        <div>${error}</div>
        <div>${message}</div>
        <button type="submit" class="submit">
            Создать аккаунт
        </button>
        <div>
            <span>Уже есть аккаунт?</span>
            <a href="${pageContext.servletContext.contextPath}/auth/login">
                Войти
            </a>
        </div>
    </form>
</div>
</body>
</html>
