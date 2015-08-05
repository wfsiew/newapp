<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>index</title>
        <asset:javascript src="home.js"/>
    </head>
    <body>
        <div ng-controller="HomeCtrl">
            <h1>hello</h1>
            <form novalidate name="frm" ng-submit="formSubmit()" method="post">
                <button name="btn" type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>