<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#import "common.ftl" as c>

<@c.page>


<div class="form-box">
    <div class="header">Talks</div>
    <div class="body">
        <form method="post" action="/talks">
            <input type="hidden" name="action" value="addTalk">
            <div class="field">
                <div class="name">
                    <label for="targetLogin">To</label>
                </div>
                <div class="value">
                    <input id="targetLogin" name="targetLogin" value="${targetLogin!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="password">Message</label>
                </div>
                <div class="value">
                    <input id="password" name="textArea" value="${textArea!}"/>
                </div>
            </div>
            <#--<div>-->
                <#--<textarea name="textArea" cols="45" rows="5" placeholder=""></textarea>-->
            <#--</div>-->
            <div class="button-field">
                <input type="submit" value="Send Message">
            </div>
            <#if error??>
                <div class="error">
                    ${error}
                </div>
            </#if>
        </form>
    </div>
</div>
<div class="datatable">
    <div class="caption">Outcoming Messages</div>
    <table>
        <thead>
        <tr>
            <th>To</th>
            <th>Message</th>
        </tr>
        </thead>
        <tbody>
        <#list talksTo as talkt>
            <tr>
                <td>
                    ${talkt.targetUserLogin}
                </td>
                <td>
                    ${talkt.text}
                </td>
            </tr>
        </#list>
    </table>
</div>
<div class="datatable">
    <div class="caption">Incoming Messages</div>
    <table>
        <thead>
        <tr>
            <th>From</th>
            <th>Message</th>
        </tr>
        </thead>
        <tbody>
        <#list talksFrom as talkf>
        <tr>
            <td>
                ${talkf.sourceUserLogin}
            </td>
            <td>
                ${talkf.text}
            </td>
        </tr>
        </#list>
    </table>
</div>
</@c.page>
