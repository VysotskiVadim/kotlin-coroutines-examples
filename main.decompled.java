                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         public final Continuation create(@Nullable Object value, @NotNull Continuation completion) {



    interface Continuation<in T> {
       val context: CoroutineContext
       fun resume(value: T)
       fun resumeWithException(exception: Throwable)
    }


    @Nullable
    public static final Object example(@NotNull Continuation $completion) {
        Object $continuation;
        label27: {
            if ($completion instanceof <undefinedtype>) {
                $continuation = (<undefinedtype>)$completion;
                if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
                    ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
                    break label27;
                }
            }

            $continuation = new ContinuationImpl($completion) {
                // $FF: synthetic field
                Object result;
                int label;
                int I$0;

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return MainKt.example(this);
                }
            };
        }

        Object var10000;
        int first;
        label22: {
            Object $result = ((<undefinedtype>)$continuation).result;
            Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch(((<undefinedtype>)$continuation).label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    ((<undefinedtype>)$continuation).label = 1;
                    var10000 = firstTask((Continuation)$continuation);
                    if (var10000 == var7) {
                        return var7;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    var10000 = $result;
                    break;
                case 2:
                    first = ((<undefinedtype>)$continuation).I$0;
                    ResultKt.throwOnFailure($result);
                    var10000 = $result;
                    break label22;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            first = ((Number)var10000).intValue();
            String var2 = "first task completed";
            boolean var3 = false;
            System.out.println(var2);
            ((<undefinedtype>)$continuation).I$0 = first;
            ((<undefinedtype>)$continuation).label = 2;
            var10000 = secondTask((Continuation)$continuation);
            if (var10000 == var7) {
                return var7;
            }
        }

        int second = ((Number)var10000).intValue();
        String var9 = "second task completed";
        boolean var4 = false;
        System.out.println(var9);
        var9 = "result is " + (first + second);
        var4 = false;
        System.out.println(var9);
        return Unit.INSTANCE;
    }

    @Nullable
    public static final Object firstTask(@NotNull Continuation $completion) {
        Object $continuation;
        label20: {
            if ($completion instanceof <undefinedtype>) {
                $continuation = (<undefinedtype>)$completion;
                if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
                    ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
                    break label20;
                }
            }

            $continuation = new ContinuationImpl($completion) {
                // $FF: synthetic field
                Object result;
                int label;

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return MainKt.firstTask(this);
                }
            };
        }

        Object $result = ((<undefinedtype>)$continuation).result;
        Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(((<undefinedtype>)$continuation).label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ((<undefinedtype>)$continuation).label = 1;
                if (DelayKt.delay(100L, (Continuation)$continuation) == var3) {
                    return var3;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        return Boxing.boxInt(1);
    }

    @Nullable
    public static final Object secondTask(@NotNull Continuation $completion) {
        Object $continuation;
        label20: {
            if ($completion instanceof <undefinedtype>) {
                $continuation = (<undefinedtype>)$completion;
                if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
                    ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
                    break label20;
                }
            }

            $continuation = new ContinuationImpl($completion) {
                // $FF: synthetic field
                Object result;
                int label;

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return MainKt.secondTask(this);
                }
            };
        }

        Object $result = ((<undefinedtype>)$continuation).result;
        Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(((<undefinedtype>)$continuation).label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ((<undefinedtype>)$continuation).label = 1;
                if (DelayKt.delay(100L, (Continuation)$continuation) == var3) {
                    return var3;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        return Boxing.boxInt(2);
    }
