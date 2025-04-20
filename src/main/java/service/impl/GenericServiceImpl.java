package service.impl;

import dao.GenericDao;
import service.GenericService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public abstract class GenericServiceImpl<T, ID> extends UnicastRemoteObject implements GenericService<T, ID>{
    protected GenericDao<T, ID> genericDAO;

    public GenericServiceImpl(GenericDao<T, ID> genericDao) throws RemoteException {
        this.genericDAO = genericDao;
    }

    public T findById(ID id) throws RemoteException{
        return genericDAO.findById(id);
    };

    public boolean save(T t) throws RemoteException{
        return genericDAO.save(t);
    };

    public List<T> getAll() throws RemoteException{
        return genericDAO.getAll();
    };
    public boolean update(T t) throws RemoteException{
        return genericDAO.update(t);
    }
}
