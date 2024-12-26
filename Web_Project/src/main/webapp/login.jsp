<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <script>
        function validateLogin() {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            if (username.trim() === "" || password.trim() === "") {
                alert("Username and Password cannot be empty!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post" onsubmit="return validateLogin()">
        <label>Username: </label>
        <input type="text" id="username" name="username"><br><br>
        <label>Password: </label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
