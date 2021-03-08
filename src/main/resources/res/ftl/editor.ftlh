<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<#import "bootstrap.ftl" as bootstrap/>
<#import "header.ftl" as header/>
<head>
    <meta charset="utf-8">
    <title>Editor</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
    <link rel="stylesheet" href="<@spring.url "/static/css/style.css"/>"/>
    <@bootstrap.import/>
    <script src="<@spring.url "/static/js/editorInit.js"/>"></script>
    <script>
        url = <@spring.url "/editor"/>;
    </script>
</head>
<body>
<@header.header pages=pages/>
<div class="editor-container">
    <div class="properties-bar">
        <input name="title" id="title" placeholder="title">
        <input name="locale" id="locale" placeholder="locale">
        <input name="path" id="path" placeholder="path">
        <select name="parentPage" id="parent-page">
            <option data-page-id="-1">Choose parent page</option>
            <#list pages as page>
                <option data-page-id="${page.id}">${page.path}</option>
            </#list>
        </select>
    </div>
    <textarea name="content" id="editor">

    </textarea>
    <button class="btn btn-success" id="submit">
        Send
    </button>
    <div class="status"></div>
</div>


</body>
</html>