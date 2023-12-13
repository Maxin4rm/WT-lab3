<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/styles.css">
</head>
<body>
<div>
    <form action="${pageContext.servletContext.contextPath}/auth/login" method="post" class="form">
        <h1>Вход</h1>
        <div >
            <label for="email">Почта:</label>
            <input type="email" id="email" name="email" required/>
        </div>
        <div>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required/>
        </div>
        <div>${error}</div>
        <button type="submit" class="submit">
            Войти
        </button>
        <div>
            <span>Нет аккаунта?</span>
            <a href="${pageContext.servletContext.contextPath}/auth/signup" class="link">
                Создать
            </a>
        </div>
    </form>
</div>
</body>
</html>
