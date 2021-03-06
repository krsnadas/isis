package org.apache.isis.core.metamodel.services.persistsession;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.bookmark.Bookmark;
import org.apache.isis.applib.services.bookmark.BookmarkService2;
import org.apache.isis.applib.services.xactn.Transaction;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.adapter.oid.Oid;
import org.apache.isis.core.metamodel.spec.ObjectSpecification;
import org.apache.isis.core.metamodel.spec.feature.OneToManyAssociation;
import org.apache.isis.core.runtime.system.transaction.TransactionalClosure;

import java.util.List;

@DomainService(nature = NatureOfService.DOMAIN)
public class PersistenceSessionServiceInternalNoop implements PersistenceSessionServiceInternal {


    @Override
    public ObjectAdapter getAdapterFor(final Object pojo) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public ObjectAdapter adapterFor(
            final Object pojo,
            final ObjectAdapter ownerAdapter,
            final OneToManyAssociation collection) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public ObjectAdapter mapRecreatedPojo(Oid oid, Object recreatedPojo) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void removeAdapter(ObjectAdapter adapter) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public ObjectAdapter adapterFor(final Object domainObject) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public ObjectAdapter getAdapterFor(Oid oid) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public ObjectAdapter createTransientInstance(final ObjectSpecification spec) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public ObjectAdapter createViewModelInstance(ObjectSpecification spec, String memento) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public Object lookup(
            final Bookmark bookmark,
            final BookmarkService2.FieldResetPolicy fieldResetPolicy) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public Bookmark bookmarkFor(Object domainObject) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public Bookmark bookmarkFor(Class<?> cls, String identifier) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void resolve(final Object parent, final Object field) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void resolve(final Object parent) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void beginTran() {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public boolean flush() {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void commit() {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void executeWithinTransaction(TransactionalClosure transactionalClosure) {
        transactionalClosure.execute();
    }

    @Override
    public Transaction currentTransaction() {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void remove(final ObjectAdapter adapter) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public void makePersistent(final ObjectAdapter adapter) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public <T> ObjectAdapter firstMatchingQuery(final Query<T> query) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

    @Override
    public <T> List<ObjectAdapter> allMatchingQuery(final Query<T> query) {
        throw new UnsupportedOperationException("Not supported by this implementation of PersistenceSessionServiceInternal");
    }

}
