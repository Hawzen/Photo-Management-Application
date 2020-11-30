public class PhotoManager {

	// Constructor
	BST<LinkedList<Photo>> inverted;
	public PhotoManager(){
		inverted = new BST<LinkedList<Photo>>();
	}
	// Add a photo
	public void addPhoto(Photo p){
		LinkedList<String> tags = p.getTags();
		tags.findFirst();
		boolean end = false;
		do {
			if (tags.last()) end = true;
			String key = tags.retrieve();
			if (!inverted.findKey(key))
				inverted.insert(key, new LinkedList<Photo>());
			inverted.retrieve().insert(p);
			tags.findNext();
		} while (!end);
	}
	// Delete a photo
	public void deletePhoto(String path){

		// Search
		inverted.findKey("Everything");
		LinkedList<Photo> photos = inverted.retrieve();

		photos.findFirst();
		Photo cur = photos.retrieve();
		while(!cur.path.equals(path)){
			photos.findNext();
			cur = photos.retrieve();
		}

		// Delete
		LinkedList<String> tags = cur.getTags();
		LinkedList<Photo> tagImgs;
		tags.findFirst();
		boolean end = false;
		do {
			if (tags.last()) end = true;
			inverted.findKey(tags.retrieve());
			tagImgs = inverted.retrieve();
			tagImgs.findFirst();
			while (true){
				if (tagImgs.retrieve().equals(cur)) {
					tagImgs.remove();
					break;
				}
				tagImgs.findNext();
			}
			tags.findNext();
		} while (!end);
	}
	// Return the inverted index of all managed photos
	public BST<LinkedList<Photo>> getPhotos(){
		return this.inverted;
	}
}
