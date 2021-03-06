package jsesh.mdcDisplayer.drawingElements.symbolDrawers;

import java.awt.geom.GeneralPath;


public class OpenErasedSymbolDelegate extends AbstractSymbolDrawer  {

	/**
	 * Produce a drawing of the sign. 
	 * The drawing is done with an angle of 0,
	 * and is supposed to fill the proposed width and height.
	 * 
	 * <p> The drawing origin is 0,0
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	protected CombinedPath buildShapeForDrawing(float width, float height) {
		GeneralPath pol = new GeneralPath();
		float halfStroke= strokeWidth/2f;
		pol.moveTo(width-halfStroke, halfStroke);
		pol.lineTo(halfStroke, halfStroke);
		pol.lineTo(halfStroke, height-halfStroke);
		pol.lineTo(width-halfStroke, height-halfStroke);
		return new CombinedPath(pol);
	}

}
