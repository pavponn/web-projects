<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#-- @ftlvariable name="user"  type="ru.itmo.webmail.model.domain.User" -->

<#import "common.ftl" as c>

<@c.page>
<div class="datatable users">
    <div class="caption">Admins</div>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <#--<th>Is admin</th>-->
            <#if user?? && user.isAdmin()>
                <th>Enable/Disable</th>
            </#if>
        </tr>
        </thead>
        <tbody>
        <tr>
            <#if user?? && user.isAdmin()>
                <td colspan="4" class="no-data">No data</td>
            <#else>
                <td colspan="3" class="no-data">No data</td>
            </#if>
        </tr>
        </tbody>
    </table>
</div>

<template class="usersTmp">
    <tr class="row">
        <td class="id">{{#}}</td>
        <td class="login">{{login}}</td>
        <#--<td class="admin"></td>-->
        <#if user?? && user.isAdmin()>
            <td>
                <div class="change">
                    <div class="id" style="display: none">${user.id}</div>
                    <button></button>
                </div>
            </td>
        </#if>
    </tr>
</template>
<#--TODO: Delete actions with column "admin"-->
<script>
    $(function () {
        var $noData = $(".users .no-data");
        $noData.text("Loading...");
        $.post("/users", {action: "find"}, function (users) {
            var $tbody = $(".users tbody");
            if (users.length > 0) {
                $tbody.empty();
                users.forEach(function (user) {
                    var $tr = cloneTemplate($("template.usersTmp"));
                    $tr.find(".id").text(user.id);
                    $tr.find(".login").text(user.login);
                    if (user.admin === true) {
                        $tr.find(".admin").text('Yes');
                        $tr.find(".change button").text('Disable');
                    } else {
                        $tr.find(".admin").text('No');
                        $tr.find(".change button").text('Enable');
                    }
                    $tr.find(".change").click(function() {
                        var button = $(this);
                        var admin = $tr.find(".admin");
                        var id = user.id;
                        var value = $(this).find('button').text();
                        var type;
                        if (value === 'Disable') {
                             $(this).find('button').text('Enable');
                             admin.text("No");
                            // $(this).closest("row").find('.admin').text('No');
                            type = 0;
                        } else {
                            $(this).find('button').text('Disable');
                            admin.text("Yes");
                            // $(this).closest("row").find('.admin').text('Yes');
                            type = 1;
                        }
                        $.post("", {action: "update", id: id, type: type}, function (response) {
                            if (response["success"]) {
                                alert("Confirm");
                            } else {
                                document.location.href = "/";
                                alert("You are not allowed to be on admins page anymore")
                            }
                        });
                        return false;
                    });
                    $tbody.append($tr);
                });
            } else {
                $noData.text("No Users");
            }
        })
    })
</script>
</@c.page>
