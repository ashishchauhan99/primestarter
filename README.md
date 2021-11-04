# To dos
- Why view need to be Serializable
- Converter and Vaidator
- Create your own compoment - done
- HttpSession -> HttpSessionCretaeListner
- HttpSession vs SpringSession
- Scaling

# JSF life cycle
For every JSF requst a view is create which is a compoment tree. This view can be accessed in the backing bean
by FacesContext.getCurrentInstance().getViewRoot();

Phase 1: Restore view
Phase 2: Apply request values
Phase 3: Process validation
Phase 4: Update model values
Phase 5: Invoke application
Phase 6: Render response

explaination: https://www.tutorialspoint.com/jsf/jsf_life_cycle.htm

# Mult column sorting in dataTable
Multiple sorting is enabled by default. In this mode, clicking a sort column while metakey is on adds sort column to the order group. Change attribute sortMode to single to allow only one column
metakey: ctrl on windows and linux

# What is the difference between action and actionListener, and when should I use action versus actionListener?
Use actionListener if you want have a hook before the real business action get executed, e.g. to log it

# Navigation

The page which wants to pass the paramter must include the following in the page header:

    <f:metadata>
        <f:viewParam name="productId" value="#{productListView.id}" />
    </f:metadata>
    
The page where you are redirected must include:

    <f:metadata>
        <f:viewParam name="productId" value="#{productView.id}" />
        <f:event type="preRenderView" listener="#{productView.init}"/> <!-- this is optional, it is used to call the method which will fetch data based on the requestParamters passed, when we access requestParamters in @PostConstructor then they will be null therefor use this tag>
    </f:metadata
    
    
        
1. Navigation can be done in two ways 

i) From frontend:
<p:link: this generates and  ``<a>`` elemnt and parmeters can be send as query parametres (after question mark)
                    <p:link outcome="/products/product.xhtml">
                         <f:param  name="productId" value="10"/>
                    </p:link>                    

<p:commandButton: this generates a <input type="submitt" parameters are send as request parameters 
                    <p:commandButton  icon="pi pi-pencil" styleClass="edit-button rounded-button ui-button-success"
                        action="/products/product.xhtml?includeViewParams=true">
                         <f:param  name="productId" value="10"/>
                    </p:commandButton>
 if we use this option then in the backing bean we need to have a @PostConstructor method and inside that we need to access FaceContext.getExternalContext#getRequestParameterMap() to get the posted parameters.               

ii) From backend: Navigation can also be done through the backing beans

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (getId() != null) {
                product = productRepository.findById(getId()).orElse(null);
            } else {
                product = new Product();
            }
        }
    }
    
When we use backend navigation then its important to use faces-redirect=true otherwise it just forward the request. It is also important to use includeViewParams=true otherweis no query parameter will be added.

NOTE: use <p:link if you do not need any kind of inbuild primefaces css styling, because there are no style class available for p:link and vice versa for <p:commandButton. With <p:commandButton its always a good idea to use backend navigation as we are using in this project.


# <f:event type="preRenderView" listener="#{productView.init}"/> vs    <f:viewAction action="#{productView.init}" />
When we use f:event then its import to use if (!FacesContext.getCurrentInstance().isPostback()) at the init method because at ever postback the init method is invoked and there will no request parameters. 

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (getId() != null) {
                product = productRepository.findById(getId()).orElse(null);
            } else {
                product = new Product();
            }
        }
    }
    
When we use f:action then we do not have to use FacesContext.getCurrentInstance().isPostback() becuase under the hood this check is already implmented by jsf and init method will not be invoked at postback.

    public void init() {
            if (getId() != null) {
                product = productRepository.findById(getId()).orElse(null);
            } else {
                product = new Product();
            }
    }


# @ViewScoped 
Note that a @ViewScoped bean does not share any data among different browser tabs/windows in the same session like as a @SessionScoped bean. Every view has its own unique @ViewScoped bean.                  
A view scoped bean lives as long as you interact with the same view (i.e. you return void or null in bean action method). When you navigate away to another view, e.g. by clicking a link or by returning a different action outcome, then the view scoped bean will be trashed by end of render response and not be available in the next request.  

# Jsf Internationalization
1. resource-bundle: used to internationalize application messages
example: https://mkyong.com/jsf2/jsf-2-internationalization-example/

2. message-bundle: used to internationalize the error and validation messages generated by jsf for eg.: lenght should be less then 5 
example: https://mkyong.com/jsf2/customize-validation-error-message-in-jsf-2-0/

## how local specific files are picked by jsf (messages_en_US.properties)
The right file is determined based on the Locale of the current request. JSF will pass the one of UIViewRoot#getLocale() to ResourceBundle (this is an api). If the name_ll_CC.properties file is absent, then the ResourceBundle will scan for name_ll.properties file. If it is absent as well, then the ResourceBundle will fallback to the default properties file whose locale you can specify as <default-locale> entry in faces-config.xml. If an entry is absent as well, then it will finally scan for name.properties instead.
https://stackoverflow.com/questions/4667542/jsf2-internationalization-property-file

## startige for changing Locale in jsf
1. using  <f:view locale="...">
2. uses a phase listene
3. by extending MultiViewHandler (we are using this in the project)
see here: https://stackoverflow.com/questions/12078520/how-to-change-locale-in-jsf-2-0


# Creating JSF custom compoment

For createing a jsf compoment we just need this annotation on the top of class

     @FacesComponent(createTag = true, tagName = "helloComponent", namespace = "http://example.com/tags")

Prior to JSF 2.0:
If you don’t use the @FacesComponent annotation, you must manually create a tag file and register it in the servlet descriptor (web.xml). Here is an example of the tag lib file.

Using the custom compoment:

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml"
          xmlns:h="http://java.sun.com/jsf/html"
          xmlns:t="http://example.com/tags">
    <h:head></h:head>
    <h:body>
        <h2>JSF Custom Component example</h2>
        <t:helloComponent message="#{helloBean.message}" time="#{helloBean.time}"/>
    </h:body>
    </html>
## Note:
- JoinFaces only scans jsf types ( @FacesComponent classes) when the project contains faces-config.xml file.

## Some useful exmples:
- https://github.com/joinfaces/joinfaces/issues/188
- https://memorynotfound.com/jsf-custom-input-facescomponent-example/
- https://www.logicbig.com/tutorials/java-ee-tutorial/jsf/custom-component-basic.html



# Jsf 2.0 generic error handler
https://stackoverflow.com/questions/18410007/jsf-2-global-exception-handling-navigation-to-error-page-not-happening

# Limiting the number of views per session
https://stackoverflow.com/questions/10988341/how-to-limit-to-store-the-no-of-views-in-session-for-prime-faces-application

- in a session com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap  is the map which actually stores the number of views / session, default is 15. 

# ViewScope beans serialization
https://stackoverflow.com/questions/36574976/server-state-serialization-in-a-session-in-mojarra