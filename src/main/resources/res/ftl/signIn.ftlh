<html>
<#import "spring.ftl" as spring>
<#import "bootstrap.ftl" as bootstrap>
<#import "header.ftl" as header/>
<head>
    <link rel="stylesheet" href="<@spring.url "/static/css/style.css"/>"/>
    <@bootstrap.import/>
</head>
<body>
<@header.header pages=pages/>
<div class="center">
    <form action="<@spring.url "/signIn"/>" class="column-container">
        <input name="login" class="placeholder form-field" placeholder="login"/>
        <input name="password" class="placeholder form-field" type="password" placeholder="password"/>
        <input class="btn btn-success" type="submit" value="submit"/>
    </form>
</div>
</body>
</html>