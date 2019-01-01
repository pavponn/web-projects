<#import "common.ftl" as c>

<@c.page>
<div class="addArticle form-box">
    <div class="header">Add Article</div>
    <div class="body">
        <form method="post" action="/article">
            <input type="hidden" name="action" value="addArticle">
            <div class="field">
                <div class="name">
                    <label for="text">Title</label>
                </div>
                <div class="value">
                    <input id="title" name="title" value="${title!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="articleText">Text</label>
                </div>
                <div class="value">
                    <textarea name="articleText" cols="45" rows="15">${articleText!}</textarea>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Post">
            </div>
            <div class="error"></div>
        </form>
    </div>
</div>

<script>
    $(function () {
        $(".addArticle form").submit(function () {
            var articleText = $(this).find("textarea[name='articleText']").val();
            var title = $(this).find("input[name='title']").val();
            var $error = $(this).find(".error").text("");
            $.post("", {action: "addArticle", articleText: articleText, title: title}, function (response) {
                if (response["success"]) {
                    setMessage("You have added an Article!");
                    document.location.href = "/";
                } else {
                    $error.text(response["error"]);
                }
            });
            return false;
        });
    })
</script>
</@c.page>
