
<style scoped>
    .layout {
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        min-height: 100%;
    }
    
    .layout-menu {
        height: 60px;
    }
    
    .layout-logo {
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }
    
    .layout-user {
        width: 300px;
        margin: auto;
        text-align: right;
        float: right;
    }
    
    .layout-nav {
        margin: 0 auto;
        margin-left: 10%;
    }
    
    .layout-assistant {
        width: 300px;
        margin: 0 auto;
        height: inherit;
    }
    
    .layout-breadcrumb {
        padding: 10px 15px 0;
    }
    
    .layout-content {
        margin: 15px;
        overflow: hidden;
        border-radius: 4px;
        max-height: 85vh;
    }
    
    .layout-content-main {
        padding: 10px;
    }
    
    .layout-copy {
        text-align: center;
        color: #9ea7b4;
        position: absolute;
        left: 0;
        bottom: 0;
        height: 5vh;
        width: 100%;
        overflow: hidden;
    }
    
    .profile-img-card {
        width: 12px;
        height: 12px;
        margin: 0 auto 10px;
        display: block;
    }
    
    #menu-icon {
        display: hidden;
        width: 40px;
        height: 40px;
        float: right;
        margin: 10px;
    }
    
    a:hover#menu-icon {
        background-color: #444;
        border-radius: 4px 4px 0 0;
    }
    
    @media only screen and (max-width: 768px) {
        /* For mobile phones: */
        #menu-icon {
            display: inline-block;
        }
        .layout-menu ul,
        .layout-menu:active ul {
            display: none;
            position: absolute;
            z-index: 99999 !important;
            height: 50%;
            margin-top: 60px;
        }
        .layout-menu li {
            text-align: center;
            width: 100%;
            padding: 10px 0;
            margin: 0;
        }
        .layout-menu:hover ul {
            display: block;
        }
    }
</style>
<template>
    <div class="layout">
        <div class="layout-menu ivu-menu-dark">
            
            <div class="layout-logo">
            </div>
            <div>
            <a id="menu-icon"></a>
            </div>

            <Menu mode="horizontal" theme="dark" active-name="1">
                <div class="layout-nav">
            
                    <router-link to="/Start">
                        <Menu-item name="2">
                            <Icon type="ios-keypad"></Icon>
                            Kwetter
                        </Menu-item>
                    </router-link>
                </div>
                <div class="layout-user">
                    <Dropdown style="line-height:20px;margin-right:6px;">
                        <a href="javascript:void(0)">
                            <i class="ivu-icon ivu-icon-android-person" style="color: white;margin-right:6px;"></i> {{fullname}}
                            <Icon type="arrow-down-b"></Icon>
                        </a>
                        <Dropdown-menu slot="list">
                            <router-link to="/Profile"><Dropdown-item>Profiel</Dropdown-item></router-link>
                            <Dropdown-item>Sign out</Dropdown-item>
                        </Dropdown-menu>
                    </Dropdown>
                </div>
            </Menu>
        </div>
        <div class="layout-breadcrumb">
            <Breadcrumb>
                <Breadcrumb-item></Breadcrumb-item>
            </Breadcrumb>
        </div>
        <div class="layout-content">
            <router-view></router-view
            <div class="layout-copy">
            </div>
        </div>
    </div>
</template>
<script>
import store from '@/store'
export default {
  name: 'test',
  components: {
  },
  data () {
    return {
      msg: 'Axios test'
    }
  },
  mounted() {
  },
  methods:{
    tryheader() {
      this.$http.get('user')
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
    }
  },
  computed: {
    fullname: function () {
      if(store.state.user != undefined) {
        return store.state.user.userName;
      } else {
        return 'NaN'
      }
      
    }
  }
}
</script>