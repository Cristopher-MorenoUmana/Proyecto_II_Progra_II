package service;

import models.CollectionDto;
import dao.CollectionDao;
import util.Response;

public class CollectionService {
    
    private final CollectionDao collectionDao = new CollectionDao();

    public CollectionService(){  
    }
    
    public Response createCollection(CollectionDto dto) {
        
        return collectionDao.addCollection(dto);
    }

    public Response updateCollection(CollectionDto dto) {
        
        return collectionDao.updateCollectionInDb(dto);
    }

    public Response deleteCollection(Integer id) {
        
        return collectionDao.deleteCollection(id);
    }

    public Response collectionsList() {
        
        return collectionDao.getCollections();
    }
}
