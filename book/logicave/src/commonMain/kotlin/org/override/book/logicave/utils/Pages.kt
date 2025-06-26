package org.override.book.logicave.utils

import logicave.book.logicave.generated.resources.Res
import logicave.book.logicave.generated.resources.pagina1_logic
import logicave.book.logicave.generated.resources.pagina2_logic
import logicave.book.logicave.generated.resources.pagina3_logic
import logicave.book.logicave.generated.resources.pagina4_logic
import logicave.book.logicave.generated.resources.pagina5_logic
import logicave.book.logicave.generated.resources.pagina6_logic
import logicave.book.logicave.generated.resources.pagina7_logic
import org.jetbrains.compose.resources.DrawableResource

enum class Pages(
    val imageResource: DrawableResource,
    val routeActual: String,
    val routeNext: String,
    val routePrevious: String,
    val text: String
) {
    PAG1(
        imageResource = Res.drawable.pagina1_logic,
        routeActual = RoutesLogic.PAG1.route,
        routeNext = RoutesLogic.PAG2.route,
        routePrevious = RoutesLogic.PAG1.route,
        text = """
        La cueva lógica tenía muchas subcuevas malditas, 
        la cueva Wire era la primera de ellas... justo en la entrada.
        La cueva wire no permitía que la luz fuera hecha a 
        menos que la antorcha fuera tomada por el portador
    """.trimIndent()
    ),
    PAG2(
        imageResource = Res.drawable.pagina2_logic,
        routeActual = RoutesLogic.PAG2.route,
        routeNext = RoutesLogic.PAG3.route,
        routePrevious = RoutesLogic.PAG1.route,
        text = """
        La siguiente subcueva maldita era la cueva not, 
        donde el ojo led brillaba con una fuerza cegadora 
        y solo podia ser apagado si el portador de la antorcha sostenia con firmeza su antorcha
    """.trimIndent()
    ),
    PAG3(
        imageResource = Res.drawable.pagina3_logic,
        routeActual = RoutesLogic.PAG3.route,
        routeNext = RoutesLogic.PAG4.route,
        routePrevious = RoutesLogic.PAG2.route,
        text = """
        La cueva or era simple. 
        Almenos uno o ambos valientes 
        debia sostener la antorcha 
        para que la luz fuera creada.
    """.trimIndent()
    ),
    PAG4(
        imageResource = Res.drawable.pagina4_logic,
        routeActual = RoutesLogic.PAG4.route,
        routeNext = RoutesLogic.PAG5.route,
        routePrevious = RoutesLogic.PAG3.route,
        text = """
        La cueva and. 
        La maldicion de esta cueva 
        se rompia cuando 
        ambos valientes tomaban sus antorchas.
        Los dos caballeros 
        se acercaban cada vez mas a un destino fatal...
    """.trimIndent()
    ),
    PAG5(
        imageResource = Res.drawable.pagina5_logic,
        routeActual = RoutesLogic.PAG5.route,
        routeNext = RoutesLogic.PAG6.route,
        routePrevious = RoutesLogic.PAG4.route,
        text = """
        Cerca de su destino se encuentran la cueva xor.
        Los dos valientes 
        no podian sostener la antorcha al mismo tiempo
        para que la luz fuera creada
    """.trimIndent()
    ),
    PAG6(
        imageResource = Res.drawable.pagina6_logic,
        routeActual = RoutesLogic.PAG6.route,
        routeNext = RoutesLogic.PAG7.route,
        routePrevious = RoutesLogic.PAG5.route,
        text = """
        La ultima cueva... la cueva latch. Una cueva peligrosa y tamprosa, 
        pues los valientes debian de ser astutos con sus antorchas. 
        Un valiente es el que debera sostener la antorcha 
        para que la luz sea creada pero el otro debera soltarla hasta el final de la cueva.
    """.trimIndent()
    ),
    PAG7(
        imageResource = Res.drawable.pagina7_logic,
        routeActual = RoutesLogic.PAG7.route,
        routeNext = RoutesLogic.PAG7.route,
        routePrevious = RoutesLogic.PAG6.route,
        text = """
        Atravesando la cueva latch, llegaron al tesoro mas valioso que se ha visto nunca, 
        pero pronto se dieron cuenta el porque no debieron olvidar sus espadas en aquella taverna...
    """.trimIndent()
    );
}