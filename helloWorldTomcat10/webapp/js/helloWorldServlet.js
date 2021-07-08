new Vue({
    el: "#app",

    data: {
        rows: []
    },

    methods: {
        sendGet() {
            $.get("/helloWorld").done(response => {
                this.rows.push({
                    id: this.rows.length + 1,
                    text: response
                });
            });
        },

        sendPost() {
            $.post("/helloWorld").done(response => {
                this.rows.push({
                    id: this.rows.length + 1,
                    text: response
                });
            });
        }
    }
});

