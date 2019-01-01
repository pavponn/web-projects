<#-- @ftlvariable name="myArticles" type="ru.itmo.webmail.model.domain.Article[]" -->
<#import "common.ftl" as c>

<@c.page>
<div class="datatable articles">
    <div class="caption">Articles</div>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Show/Hide</th>
        </tr>
        </thead>
        <tbody>
        <#list myArticles as myArticle>
        <tr>
            <td>
                ${myArticle.id}
            </td>
            <td>
                ${myArticle.title}
            </td>
            <td>
                <div class="show-hide-button">
                    <div class="id" style="display: none">${myArticle.id}</div>
                    <div class="userId" style="display: none">${myArticle.userId}</div>
                    <button><#if myArticle.isHidden() == true>Show<#else>Hide</#if></button>
                </div>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>

<script>
    $(".show-hide-button").click(function () {
        var id = $(this).find('.id').text();
        var userId = $(this).find('.userId').text();
        var value = $(this).find('button').text();
        var type;
        if (value === 'Show') {
            $(this).find('button').text('Hide');
            type = 0;
        } else {
            $(this).find('button').text('Show');
            type = 1;
        }
         $.post("", {action: "update", id: id, userId: userId, type: type});
        return false;
    });
</script>
</@c.page>
