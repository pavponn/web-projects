<#import "common.ftl" as c>

<@c.page>
    <#if message??>
        <p>${message}</p>
    </#if>
<div class = "articles">
</div>
<template class = "article-tr">
    <div class="article">
        <article>
            <div class = "title">{{title}}</div>
            <div class = "information">By: {{author}} at {{time}}</div>
            <div class = "body"> {{text}} </div>
            <div class="footer">
                <div class="left">
                    <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">42</a>
                </div>
            </div>
        </article>
    </div>

</template>
<script>
    $(function () {
        $.post("", {action: "findArticles"}, function (articles) {
            var $articles = $(".articles");
            if (articles.length > 0) {
                $articles.empty();
                articles.forEach(function (articlePiece) {
                    var $article = cloneTemplate($("template.article-tr"));
                    $article.find(".title").text(articlePiece.article.title);
                    $article.find(".body").text(articlePiece.article.text);
                    $article.find(".information").text("By: " + articlePiece.login + " at " + articlePiece.article.creationTime);
                    $article.find(".time").text(articlePiece.article.creationTime);
                    // $article.find(".author").text("By: " + article.userId);
                    $articles.append($article);
                });
            } else {
                $noData.text("No data");
            }
        })
    })
</script>
</@c.page>