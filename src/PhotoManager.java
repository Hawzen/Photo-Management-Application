public class PhotoManager {

	// Constructor
	BST<LinkedList<Photo>> inverted;
	public PhotoManager(){
		inverted = new BST<LinkedList<Photo>>();
	}

	// Add a photo
	public void addPhoto(Photo p){
		// FIXME: Check if the photo exists using BST's linked and dont add it if it does
		if (exists(p, inverted.getAllPhotos()))
			return;

		LinkedList<String> tags = p.getTags();
		// FIXME: What if the condition is empty
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

		// FIXME: BST should contain linked list
	}

	// Delete a photo
	public void deletePhoto(String path){

		// Search
		// FIXME replace "Everything" wih BST's linked list
		inverted.findKey("Everything");
		LinkedList<Photo> photos = inverted.retrieve();

		photos.findFirst();
		if(photos.empty()) return;
		Photo cur = photos.retrieve();
		while(!cur.path.equals(path)){
			photos.findNext();
			cur = photos.retrieve();
			if(cur == null) return;
		}

		// Delete
		LinkedList<String> tags = cur.getTags();
		LinkedList<Photo> tagImgs;
		tags.findFirst();
		// FIXME: Handle empty tags case
		boolean end = false;
		do {
			if (tags.last()) end = true;
			inverted.findKey(tags.retrieve());
			tagImgs = inverted.retrieve();
			tagImgs.findFirst();
			while (true){
				if (tagImgs.retrieve().equals(cur)) {
					tagImgs.remove();
					if (tagImgs.empty())
						inverted.removeKey(tags.retrieve());
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

	private <T> boolean exists(T p, LinkedList<T> ll) {
		if(ll.empty()) return false;

		boolean flag = false;
		ll.findFirst();
		while(true) {
			if(p.equals(ll.retrieve()))
				flag = true;
			if(ll.last()) break;
			ll.findNext();
		}

		return flag;
	}

}
