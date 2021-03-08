<html>
<#import "spring.ftl" as spring>
<#import "bootstrap.ftl" as bootstrap>
<#import "header.ftl" as header/>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<@spring.url "/static/css/style.css"/>"/>
    <@bootstrap.import/>
</head>
<body>
<@header.header pages=pages/>
<div class="content">
    ${content}
</div>
</body>
</html>