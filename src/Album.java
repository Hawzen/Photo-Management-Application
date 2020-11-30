public class Album {
	String name;
	String condition;
	PhotoManager manager;
	// Constructor
	public Album(String name, String condition, PhotoManager manager){
		this.name = name;
		this.condition = condition.isEmpty() ? "Everything" : condition;
		this.manager = manager;
	}
	// Return the name of the album
	public String getName(){
		return this.name;
	}

	// Return the condition associated with the album
	public String getCondition(){
		return this.condition;
	}

	// Return the manager
	public PhotoManager getManager(){
		return this.manager;
	}

	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos(){
		// Create a linked list of tags
		LinkedList<Photo> output = new LinkedList<Photo>();
		String[] tags = this.condition.split(" AND ");

		BST<LinkedList<Photo>> bst = manager.getPhotos();
		// FIXME: Check if all tags are in BST
		LinkedList<Photo> everyPhoto = bst.getAllPhotos();
		everyPhoto.findFirst();
		if(everyPhoto.empty()) return everyPhoto;

		Photo cur;
		boolean inTags, inTag; // inTags: Photo exists in all relevant tags, inTag: photo is in tagImgs
		boolean end = false, end2 = false;
		LinkedList<Photo> tagImgs;
		do { // For each image
			if (everyPhoto.last()) end = true;
			cur = everyPhoto.retrieve();
			inTags = true;
			for (int i = 0; i < tags.length; i++) { // For each tag
				bst.findKey(tags[i]);
				tagImgs = bst.retrieve();
				tagImgs.findFirst();

				inTag = false;
				end2 = false;
				do { // For each image in each tag
					if (tagImgs.last()) end2 = true;
					if (tagImgs.retrieve().equals(cur)) {
						inTag = true;
						break;
					}
					tagImgs.findNext();
				}
				while (!end2);
				if (!inTag) { // If not found in tagImgs, then dont return pic and break
					inTags = false;
					break;
				}
			}
			if (inTags)
				output.insert(cur);
			everyPhoto.findNext();
		} while (!end);
		return output;
	}

	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps(){
		BST<LinkedList<Photo>> bst = manager.getPhotos();
		int counter = 0;

		String[] tags = this.condition.split(" AND ");
		for (int i=0; i < tags.length; i++)
			counter += bst.getNbComp(tags[i]);

		return counter;
	}
}
