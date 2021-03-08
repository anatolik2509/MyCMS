<#import "spring.ftl" as spring/>
<#macro header pages>
    <header class="site-header">
        <div class="dropdown header-element">
            <img class="dropdown-menu-icon dropdown-toggle" src="<@spring.url "/static/img/burger.png"/>" id="dropdownMenuButton" data-bs-toggle="dropdown"/>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <#list pages as page>
                    <li><a href="<@spring.url "${page.path}"/>">${page.path}</a></li>
                </#list>
            </ul>
        </div>
        <h1 class="header-element">This is site</h1>
    </header>
</#macro>