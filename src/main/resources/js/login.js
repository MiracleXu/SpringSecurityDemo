var loginView = new Vue({
    el: '#loginView',
    data() {
        return {
            loginForm: {
                userId: null,
                password: null
            },
        }
    },
    mounted: function () {//vue加载完成后执行
        var self = this;
        self.init();
    },
    methods: {
        login: function () {
            var self = this;
            console.log(self.loginForm.password+"---"+self.loginForm.userId);
        },
        init() {
            var self = this;
            axios({
                method: 'get',
                url: 'http://localhost:8081/index'
            }).then(function (response) {
                if (response.data) {
                    self.loginForm.userId = response.data;
                } else {
                    self.$alert('失败！\n', '提示');
                }
            }).catch(function (error) {
                self.$alert('失败！', '提示');
            });
        }
    }
})