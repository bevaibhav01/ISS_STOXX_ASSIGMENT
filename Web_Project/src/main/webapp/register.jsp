<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <script>
        function validateRegistration() {
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
    <h2>Register</h2>
    <form action="RegisterServlet" method="post" onsubmit="return validateRegistration()">
        <label>Username: </label>
        <input type="text" id="username" name="username"><br><br>
        <label>Password: </label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
