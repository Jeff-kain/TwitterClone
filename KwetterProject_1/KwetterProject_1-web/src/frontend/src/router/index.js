import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Start from '@/pages/Start'

Vue.use(Router)

export default new Router({
  routes: [  
    {
    path: '/',
      name: 'home',
      component: require('../pages/Home.vue'),
           meta: {
                parent: [''],
                breadcrumb: 'Home'
            },
            children: [{
            
      path: '/start',
      name: 'Start',
      component: Start,
          meta: {
                        parent: ['/'],
                        breadcrumb: 'start'
                    },
    },
     {                
          path: '/profile',
          name: 'Profile',
          component: require('../pages/Profile.vue'),
              meta: {
                        parent: ['/'],
                        breadcrumb: 'profile'
                    },
        }
            ]
          },
        {
           path: '/login',
           name: 'Login',
           component: require('../pages/Login.vue')
            
    },
  ]
})
