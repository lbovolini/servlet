<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>User registration</title>
    </head>
    <body>
        <form action="register" method="POST">
            <table>
                <tr>
                    <td>First Name</td>
                    <td>
                        <input type="text" name="firstName"/>
                    </td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td>
                        <input type="text" name="lastName"/>
                    </td>
                </tr>
                <tr>
                    <td>Email Address</td>
                    <td>
                        <input type="text" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>Phone number</td>
                    <td>
                        <input type="text" name="phone"/>
                    </td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td>
                        <input type="text" name="birthday"/>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>