package filters;

import utils.ImageMatrix;

public abstract class Filter  {
	public abstract ImageMatrix apply(ImageMatrix image);
}
