<html>
<#import "spring.ftl" as spring>
<#import "bootstrap.ftl" as bootstrap>
<#import "header.ftl" as header/>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${staticPath}/static/css/style.css"/>
    <@bootstrap.import/>
</head>
<body>
<header class="site-header">
    <div class="dropdown header-element">
        <img class="dropdown-menu-icon dropdown-toggle" src="${staticPath}/static/img/burger.png" id="dropdownMenuButton" data-bs-toggle="dropdown"/>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <#list pages as page>
                <li><a href="<@spring.url "${page.path}"/>">${page.path}</a></li>
            </#list>
        </ul>
    </div>
    <h1 class="header-element">This is site</h1>
</header>
<div class="content">
    ${content}
</div>
</body>
</html>