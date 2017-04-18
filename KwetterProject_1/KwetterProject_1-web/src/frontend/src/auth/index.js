import router from '@/router'
import request from '@/utils/request'
import store from '@/store'

export default {
    user: {
        authenticated: false
    },
    login(creds, redirect) {
        request.post('authentication', creds)
            .then((response) => {
                if (response != "UNAUTHORIZED") {
                    localStorage.setItem('access_token', response.access_token)
                
                    this.user.authenticated = true

                    if (redirect) {
                        request.get('kwetter-api/currentuser')
                            .then((response) => {
                                store.commit('login', response)
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                        router.push(redirect)
                    }
                }
            })
            .catch((error) => {
                console.log(error);
            });
    },

    register(creds, redirect) {
        request.post('user', creds)
            .then((response) => {

                if (redirect) {
                    router.push(redirect)
                }

            })
            .catch((error) => {
                return error;
            });
    },

    logout() {
        localStorage.removeItem('access_token')
        this.user.authenticated = false
    },

    checkAuth() {
        var jwt = localStorage.getItem('access_token')
        if (jwt) {
            this.user.authenticated = true
        } else {
            this.user.authenticated = false
        }
    },

    getAuthHeader() {
        return {
            'Authorization': 'Bearer ' + localStorage.getItem('access_token')
        }
    }
}