<style>
  body,
  html {
    background-position: top center;
    background-size: 100% auto;
    background-attachment: fixed;
    background-color:  #e6e9ec;

        overflow-y: scroll;

  }
.pv-top-card-section__edit-photo {
    background-color: transparent;
}
.profile-photo-edit {
    position: relative;
    background-color: #ffffff;
    display: flex;
    align-items: left;
    border-radius:20px;
}
.imageUpload
{
    display: none;
}

.profileImage
{
    cursor: pointer;
    height:40%;
    width: 40%;
    border-radius:20px;
    
}
 .tweet {
    height: 115px;
    width: 100%;
    padding-left: 20px;
    padding-top: 10px;
    background-color: #ffffff;
    border: solid 1px grey;
    border-width: 1px 1px 0 1px;
  }
.trends {
   position: relative;
    background-color: #ffffff;
    display: flex;
    flex-direction: column;
    align-items: left;
    box-sizing: border-box;
    border-radius:20px;
    margin-top:10%;
    height:500px;
}
.kweets {
   position: relative;
    border-radius:5px;
    height:100px;
    align-items:left;
    
}
.timeline {
    margin-top:10%;

}
.centerContent {
    margin-left: 25%;
    margin-right: 30%;
    width:35%;
  
}
.mainContent {
    margin-left: 20%;
    margin-top:40px;
}
.Profile-and-trends {
    margin-left: 1%;
    padding-right: 1%;
    float: left;
    width:20%;
}
.firstContent {
    margin-right: 1%;
}
.timelineMessage {
    position: relative;
    background-color: #ffffff;
    align-items: left;
    border-radius:10px;
    height:80px;
    margin-top:10px;
}
.ProfileCardStats {
    margin-left: 11px;
    padding: 5px 0;
    list-style-type:none;
    cursor: pointer;
}
.Arrange--equal {
    table-layout: fixed;
}

.Arrange {
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    display: table;
    margin: 0;
    min-width: 100%;
    padding: 0;
    table-layout: auto;
}
    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-content {
        height: 120px;
        margin-top: -16px;
    }
    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-content > .ivu-tabs-tabpane {
        background: #fff;
        padding: 16px;
    }
    .demo-tabs-style1 > .ivu-tabs.ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab {
        border-color: transparent;
    }
    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab-active {
        border-color: #fff;
    }
    .demo-tabs-style2 > .ivu-tabs.ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab{
        border-radius: 0;
        background: #fff;
    }
    .demo-tabs-style2 > .ivu-tabs.ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab-active{
        border-top: 1px solid #3399ff;
    }
    .demo-tabs-style2 > .ivu-tabs.ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab-active:before{
        content: '';
        display: block;
        width: 100%;
        height: 1px;
        background: #3399ff;
        position: absolute;
        top: 0;
        left: 0;
    }
    .h1 {
        align: center;
        color: blue;
        margin-left:25%;
    }
.ProfileCardStats-statValue {   }
</style>
<template>
  <div class="mainContent" >
  <div class="firstContent">
   <div class="Profile-and-trends">
      <div id="ember5808" class="pv-top-card-section__edit-photo profile-photo-edit">
  <img id="profileImage" src="../images/Donald_pepe.png"/>
<input id="imageUpload" type="file" 
       name="profile_photo" placeholder="Photo" required="" capture>
       <div class="ProfileCardStats">
    <a class="ProfileCardStats-statList Arrange Arrange--bottom Arrange--equal"><li class="ProfileCardStats-stat Arrange-sizeFit">
        <a class="ProfileCardStats-statLink u-textUserColor u-linkClean" title="0 Tweets">
          <span class="ProfileCardStats-statLabel u-block">kweets</span>
          <span class="ProfileCardStats-statValue" data-count="0">0</span>
        </a>
      </li><li class="ProfileCardStats-stat Arrange-sizeFit">
          <a class="ProfileCardStats-statLink u-textUserColor u-linkClean" data-original-title="12 following">
            <span class="ProfileCardStats-statLabel u-block">following</span>
            <span class="ProfileCardStats-statValue" data-count="12" data-is-compact="false">12</span>
          </a>
        </li><li class="ProfileCardStats-stat Arrange-sizeFit">
          <a class="ProfileCardStats-statLink u-textUserColor u-linkClean" data-original-title="3 followers">
            <span class="ProfileCardStats-statLabel u-block">followers</span>
            <span class="ProfileCardStats-statValue" data-count="3" data-is-compact="false">3</span>
          </a>
        </li>
    </a>
  </div>
  </div>  
  <strong>{{this.formdata.username}}</strong> 

  <div id="trends" class="trends pv-top-card-section__edit-photo" >
  <h1 class="h1"> Trends </h1>
  <span class="ProfileCardStats">#Test</span>
    <p class="ProfileCardStats" >#Test2</p>

  </div>
  </div>
  <div class="centerContent">
    <div id="kweets" class="kweets" >
  <Input type="textarea" class="kweets" rows="4" cols="55" v-model="formdata.content">
</Input>
 <button class="btn-primary" type="primary" @click="addkweet()" long>
            <span>Add Kweet</span>
        </button>
     </div>
    <div id="timeline" class="timeline">
    </div>
    <Tabs type="card" :animated="false">
        <Tab-pane label="Kweets">
        <div class="col-md-12">
      <div class="tweet timelineMessage" v-for="n in 3">
        <div class="col-md-12">
          <div class="col-md-9">
            <p><strong>Jeff</strong> @Jeff - 4 weeks ago</p>
          </div>
          <div class="col-md-12">
            <p>Hallo{{n}}</p>
          </div>
        </div>
      </div>

    </div>
   </Tab-pane>
    <Tab-pane label="Mentions">
          <div class="tweet timelineMessage">
        <div class="col-md-12">
          <div class="col-md-9">
            <p><strong>Jeff</strong> @Jeff - 4 weeks ago</p>
          </div>
          <div class="col-md-12">
            <p>Hallo @Jeff</p>
          </div>
        </div>
        </div>
        </tab-pane>
    </Tabs>
  </div>
  </div>
  </div>
</template>
<script>
import request from '@/utils/request'
    export default {
        data () {
            return {
                  formdata: {
                         content: ''
        }
            }
        }, 
          methods: {
            addkweet() {
                    request
                    .post("kwetter-api/kweets", this.formdata.content)
                    .then((response) => {
                       
                    })
                    .catch((error) => {
                        console.log(error);
                    });
                
            }
    },      
    }
</script>
