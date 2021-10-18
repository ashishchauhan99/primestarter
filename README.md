# To dos
JSF life cycle
Converter and Vaidator
Create your own compoment
HttpSession -> HttpSessionCretaeListner
HttpSession vs SpringSession
Scaling


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


 
                    