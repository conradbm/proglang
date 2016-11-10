(define Interpreter
 (lambda (E env)
   (cond 
     ((constx? E) (eval_constx E env))
     ((sumx? E) (eval_sumx E env))
     ((productx? E) (eval_productx E env))
     ((variable? E) (eval_variable E env))
     ((ifx? E) (eval_ifx E env))
     ((lessx E) (eval_lessx E env))
     ((letx? E) (eval_letx E env))
     ((printx? E) (eval_printx E env))
     ((dox? E) (eval_dox E env))
     (else
       (error "Invalid expression" E)))))

;;;;;;;;;;;;;;;;;
(define dox?
  (lambda (E)
 (and (pair? E) (equal? 'dox (car E)))))

 (define eval_dox
  (lambda (E env)
    (cond
      ((number? (cadr E)) 
            (do ((x 1 (+ x 1)))((> x (cadr E)) (display "Loop finished\n"))
              (display (caddr E))(newline)))
            (else
       (do ((x 1 (+ x 1)))((> x (car (cdar env))) (display "Loop Finished\n"))
         (display (caddr E))(newline))))))

;;;;;;;;;;;;;;;;;
(define printx?
  (lambda (E)
    (and (pair? E) 
    (equal? 'printx (car E)))))

(define eval_printx
  (lambda (E env)
    (display (cdr E))))
;;;;;;;;;;;;;;;;;

(define applyx
   (lambda (op ls)
     (cond
       ((null? ls) (op))
       (else
       (op (car ls) (applyx op (cdr ls)))))))

 (define mapx
   (lambda (f ls)
     (cond
       ((null? ls) '())
       (else
         (cons (f (car ls)) (mapx f (cdr ls)))))))
 
;;;;;;;;;;;;;;;;;
 
(define constx? number?)

(define eval_constx
  (lambda (E env)
    E))

(define sumx?
  (lambda (E)
    (and (pair? E) (or (equal? 'sumx (car E)) (equal? '+ (car E))))))

(define eval_sumx
  (lambda (E env)
    (applyx + (mapx (lambda (x) (Interpreter x env)) (cdr E)))))

(define productx?
  (lambda (E)
    (and (pair? E)  (or (equal? 'productx (car E)) (equal? '* (car E))))))

(define eval_productx
  (lambda (E env)
    (applyx * (mapx (lambda (x) (Interpreter x env)) (cdr E)))))

;;;;;;;;;;

(define pairVarVal
  (lambda (x ls)
    (cond
      ((null? ls) '())
      ((eq? (caar ls) x) (car ls))
      (else
       (pairVarVal x (cdr ls))))))

 (define variable? symbol?)
 
 (define eval_variable
   (lambda (E env)
     (let ((matchPair (pairVarVal E env))) (cadr matchPair))))

;;;;;;;;;;;
 
(define ifx?
  (lambda (E)
    (and (pair? E) (equal? 'ifx (car E))))) 


(define eval_ifx
  (lambda (E env)
;      (if (cadr E) (Interpreter (caddr E) env) (Interpreter (cadddr E) env))))
   (cond
     ((Interpreter (cadr E) env) (Interpreter (caddr E) env))
     (else
       (Interpreter (cadddr E) env)))))

;;;;;;;;;

(define lessx
  (lambda (E)
    (and (pair? E) (or (equal? 'lessx (car E)) (equal? '< (car E)))))) 

(define eval_lessx
  (lambda (E env)
      (apply < (mapx (lambda (x) (Interpreter x env)) (cdr E)))))

;;;; let* ;;;;;
;(define x 0)
 ; (let ((x 2)(y x)) y)
 ; (let* ((x 2)(y x)) y)
;;;;;;;;;;;;;
  
  (define tie-all
    (lambda (vars values old-env)
      (append (map list vars values) old-env)))
  
(define letx?
  (lambda (E)
    (and (pair? E) (equal? 'letx (car E)))))

(define eval_letx
  (lambda (E old-env)
    (let* ((vars (map car (cadr E)))
           (expres (map cadr (cadr E)))
           (values (map (lambda (x) (Interpreter x old-env)) expres))
           (new-env (tie-all vars values old-env)))
    (Interpreter (caddr E)new-env))))

;;;;;;;;;;;;;;;;;;;;;;;;;
(Interpreter '(dox n "Hello") '((n 25)))
