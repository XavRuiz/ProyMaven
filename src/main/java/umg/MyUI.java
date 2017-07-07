package umg;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout hlayout = new HorizontalLayout ();
        
        final TextField name = new TextField();
        name.setCaption("Escribe tu nombre acá:");

        final TextField age = new TextField();
        age.setCaption("Edad:");

        List <Estudiante> listadoEstudiantes = new ArrayList<>();
        listadoEstudiantes.add(new Estudiante( "Javier",  27));

        Grid<Estudiante> grid = new Grid<>();
        grid.setItems(listadoEstudiantes);
        grid.addColumn(Estudiante::getNombre).setCaption("Nombre");
        grid.addColumn(Estudiante::getEdad).setCaption("Edad");


        Button button = new Button("Agregar");
        /*button.addClickListener( e -> {
            layout.addComponent(new Label("Gracias " + name.getValue()
                    + ", El proyecto está funcionando!"));
        });*/

        button.addClickListener( e -> {

            listadoEstudiantes.add(new Estudiante(name.getValue(),Integer.parseInt(age.getValue())));
            grid.setItems(listadoEstudiantes);
            name.clear();
            age.clear();
            Notification.show("Estudiante agregar");

        });
        
        hlayout.addComponents(name, age, button);
        hlayout.setComponentAlignment(button,Alignment.BOTTOM_RIGHT);
        //layout.addComponent(hlayout, grid);
        layout.addComponents(hlayout, grid);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
