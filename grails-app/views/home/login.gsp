<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>index</title>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div>${flash.message}</div>
        </g:if>
        <form name="frm" method="post" action="auth">
            <input name="username" type="text" />
            <input name="password" type="password" />
            <button type="submit">Login</button>
        </form>
    </body>
</html>