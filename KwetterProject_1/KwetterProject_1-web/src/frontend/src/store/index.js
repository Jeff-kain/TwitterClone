import vue from 'vue'
import Vuex from 'vuex'
vue.use(Vuex)


const store = new Vuex.Store({
    state: {
        user: 'Jeff',
    },
    mutations: {
        login(state, loggeduser) {
            state.user = loggeduser;
        }
    }
})

export default store