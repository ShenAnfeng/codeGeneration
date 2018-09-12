/**
 * @author GeekTantra
 * @date 20 September 2009
 */
(function(jQuery){
    var ValidationErrors = new Array();
    jQuery.fn.validate = function(options){
        options = jQuery.extend({
            expression: "return true;",
            message: "",
            expression2: "return true;",
            message2: "",
            error_class: "ValidationErrors",
            error_field_class: "ErrorField",
            right_class: "ValidationRight",
            right_field_class: "RightField",
            live: true
        }, options);
        var SelfID = jQuery(this).attr("id");
        var unix_time = new Date();
        unix_time = parseInt(unix_time.getTime() / 1000);
        if (!jQuery(this).parents('form:first').attr("id")) {
            jQuery(this).parents('form:first').attr("id", "Form_" + unix_time);
        }
        var FormID = jQuery(this).parents('form:first').attr("id");
        if (!((typeof(ValidationErrors[FormID]) == 'object') && (ValidationErrors[FormID] instanceof Array))) {
            ValidationErrors[FormID] = new Array();
        }
        if (options['live']) {
            if (jQuery(this).find('input').length > 0) {
                jQuery(this).find('input').bind('blur', function(){
                    if (validate_field("#" + SelfID, options)) {
                        if (options.callback_success) 
                            options.callback_success(this);
                    }
                    else {
                        if (options.callback_failure) 
                            options.callback_failure(this);
                    }
                });
                jQuery(this).find('input').bind('focus keypress click', function(){
                    jQuery("#" + SelfID).next('.' + options['error_class']).remove();
                    jQuery("#" + SelfID).removeClass(options['error_field_class']);
                });
            }
            else {
                jQuery(this).bind('blur', function(){
                    validate_field(this);
                });
                jQuery(this).bind('focus keypress', function(){
                    jQuery(this).next('.' + options['error_class']).fadeOut("fast", function(){
                        jQuery(this).remove();
                    });
					
                    jQuery(this).next('.' + options['right_class']).fadeOut("fast", function(){
                        jQuery(this).remove();
                    });
                    jQuery(this).removeClass(options['error_field_class']);
					jQuery(this).removeClass(options['right_field_class']);
                });
            }
        }
        jQuery(this).parents("form").submit(function(){
            if (validate_field('#' + SelfID)) 
                return true;
            else 
                return false;
        });
        function validate_field(id){
            var self = jQuery(id).attr("id");
            var expression = 'function Validate(){' + options['expression'].replace(/VAL/g, 'jQuery(\'#' + self + '\').val()') + '} Validate()';
            
            var validation_state = eval(expression);
            
           var expression2 = 'function Validate(){' + options['expression2'].replace(/VAL/g, 'jQuery(\'#' + self + '\').val()') + '} Validate()';
           var validation_state2 = eval(expression2);
           
            if (!validation_state) {
                if (jQuery(id).next('.' + options['error_class']).length == 0) {
                    jQuery(id).after('<span class="' + options['error_class'] + '">' + options['message'] + '</span>');
                    jQuery(id).addClass(options['error_field_class']);
                }
                if (ValidationErrors[FormID].join("|").search(id) == -1) 
                    ValidationErrors[FormID].push(id);
                return false;
            }
          else if(!validation_state2) {
                if (jQuery(id).next('.' + options['error_class']).length == 0) {
                    jQuery(id).after('<span class="' + options['error_class'] + '">' + options['message2'] + '</span>');
                    jQuery(id).addClass(options['error_field_class']);
                }
                if (ValidationErrors[FormID].join("|").search(id) == -1) 
                    ValidationErrors[FormID].push(id);
                return false;
            }
           
            else {
            		if (jQuery(id).next('.' + options['right_class']).length == 0) {
                    jQuery(id).after('<span class="' + options['right_class'] + '">��</span>');
                    jQuery(id).addClass(options['right_field_class']);
                }
                for (var i = 0; i < ValidationErrors[FormID].length; i++) {
                    if (ValidationErrors[FormID][i] == id) 
                        ValidationErrors[FormID].splice(i, 1);
                }
                return true;
            }
        }
    };
    jQuery.fn.validated = function(callback){
        jQuery(this).each(function(){
            if (this.tagName == "FORM") {
                jQuery(this).submit(function(){
                    if (ValidationErrors[jQuery(this).attr("id")].length == 0) 
                        callback();
											return false;
                });
            }
        });
    };
})(jQuery);