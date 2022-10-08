/*
 * Copyright (c) 2016, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.jfr.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jdk.jfr.internal.consumer.ObjectContext;

/**
 * A recorded stack trace.
 *
 * @since 9
 */
public final class RecordedStackTrace extends RecordedObject {
    // package private
    RecordedStackTrace(ObjectContext objectContext, Object[] values) {
        super(objectContext, values);
    }

    /**
     * Returns the frames in the stack trace.
     *
     * @return a list of Java stack frames, not {@code null}
     */
    @SuppressWarnings("unchecked")
    public List<RecordedFrame> getFrames() {
        Object[] array = getTyped("frames", Object[].class, null);
        if (array == null) {
            return new ArrayList<>(0);
        }
        List<?> list = Arrays.asList(array);
        return (List<RecordedFrame>) list;
    }

    /**
     * Returns {@code true} if the stack trace is truncated due to its size,
     * {@code false} otherwise.
     *
     * @return {@code true} if the stack trace is truncated, {@code false}
     *         otherwise
     */
    public boolean isTruncated() {
        return getTyped("truncated", Boolean.class, true);
    }
}
