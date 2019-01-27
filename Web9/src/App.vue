<template>
    <!--suppress HtmlUnknownTag -->
    <body id="app">
    <Header :userId="userId" :users="users"/>
    <Middle :users="users" :posts="posts"/>
    <Footer/>
    </body>
</template>

<script>
    import Header from './components/Header'
    import Middle from './components/Middle'
    import Footer from './components/Footer'

    export default {
        name: 'app',
        data: function () {
            return this.$root.$data;
        },
        components: {
            Header,
            Middle,
            Footer
        }, beforeCreate: function () {
            this.$root.$on("onLogout", () => {
                this.userId = null;
            });

            this.$root.$on("onEnter", (login) => {
                let users = Object.values(this.users).filter(u => u.login === login);
                if (users.length) {
                    this.userId = users[0].id;
                    this.$root.$emit("onEnterSuccess");
                } else {
                    this.$root.$emit("onEnterValidationError", "Invalid login/password.");
                }
            });
            let validation = (login, name) => {
                if (!(/^[a-z]+$/.test(login))) {
                    return "Login must contain only latin lowercase letters.";
                }
                if (login.length < 3) {
                    return "Login can't be shorter than 3 symbols.";
                }
                if (login.length > 16) {
                    return "Login can't be longer than 16 symbols.";
                }
                if (name.length > 32) {
                    return "Name can't be longer than 32 symbols.";
                }
                if (name.length < 1) {
                    return "Name can't be empty.";
                }
                return "success";
            };

            this.$root.$on("onRegister", (login, name) => {
                let result = validation(login, name);
                if (result !== "success") {
                    this.$root.$emit("onRegisterValidationError", result);
                    return;
                }
                let users = Object.values(this.users).filter(user => user.login === login);
                if (users.length !== 0) {
                    this.$root.$emit("onRegisterValidationError", "Login is in use");
                    return;
                }

                let id = Math.max(...Object.keys(this.users)) + 1;

                this.$set(this.users, id, {
                    id,
                    login,
                    name,
                    admin: false
                });

                alert("You have been registered!");
                this.userId = id;
                this.$root.$emit("onEnterSuccess");
            });

            this.$root.$on("onAddPost", (title, text) => {
                if (this.userId) {
                    if (!title || title.length > 16) {
                        this.$root.$emit("onAddPostValidationError", "Title is invalid");
                    } else if (!text || text.length < 5) {
                        this.$root.$emit("onAddPostValidationError", "Text is invalid");
                    } else {
                        const id = Math.max(...Object.keys(this.posts)) + 1;
                        this.$set(this.posts, id, {
                            id,
                            userId: this.userId,
                            title,
                            text
                        })
                    }
                } else {
                    this.$root.$emit("onAddPostValidationError", "No access");
                }
            });
            this.$root.$on("onEditPost", (id, text) => {
                if (this.userId) {
                    if (!id) {
                        this.$root.$emit("onEditPostValidationError", "ID is invalid");
                    } else if (!text || text.length > 10) {
                        this.$root.$emit("onEditPostValidationError", "Text is invalid");
                    } else {
                        let posts = Object.values(this.posts).filter(p => p.id === parseInt(id));
                        if (posts.length) {
                            posts.forEach((item) => {
                                item.text = text;
                            });
                        } else {
                            this.$root.$emit("onEditPostValidationError", "No such post");
                        }
                    }
                } else {
                    this.$root.$emit("onEditPostValidationError", "No access");
                }
            });
        }
    }
</script>

<style>
</style>
