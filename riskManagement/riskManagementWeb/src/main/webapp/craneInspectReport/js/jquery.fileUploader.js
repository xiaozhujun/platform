/*
*	Class: fileUploader
*	Use: Upload multiple files using jquery
*	Author: John Laniba (http://pixelcone.com)
*	Version: 1.3
*/

(function($) {
	$.fileUploader = {version: '1.3', count: 0};
	$.fn.fileUploader = function(config){
		
		config = $.extend({}, {
			autoUpload: false,
			limit: false,
			buttonUpload: '#px-submit',
			buttonClear: '#px-clear',
			selectFileLabel: '选择文件',
			allowedExtension: 'xls',
			timeInterval: [1, 2, 4, 2, 1, 5], //Mock percentage for iframe upload
			percentageInterval: [10, 20, 30, 40, 60, 80],
			
			//Callbacks
			onValidationError: null,	//trigger if file is invalid
			onFileChange: function(){},
			onFileRemove: function(){},
			beforeUpload: function(){}, //trigger after the submit button is click: before upload
			beforeEachUpload: function(){}, //callback before each file has been uploaded ::: returns each Form
			afterUpload: function(){},
			afterEachUpload: function(){} //callback after each file has been uploaded
			
		}, config);
		
		$.fileUploader.count++;
		
		//Multiple instance of a FOrm Container
		var pxUploadForm = 'px-form-' + $.fileUploader.count,
		pxWidget = 'px-widget-' + $.fileUploader.count,
		pxButton = 'px-button-' + $.fileUploader.count,
		wrapper = ' \
			<div id="'+ pxWidget +'" class="px-widget ui-widget"> \
				<div class="ui-helper-clearfix"> \
					<div id="'+ pxUploadForm +'-input" class="px-form-input"></div> \
					<div id="'+ pxButton +'" class="px-buttons"></div> \
				</div> \
				<div id="'+ pxUploadForm +'"></div> \
			</div> \
		',
		pxUploadForm = '#' + pxUploadForm,
		pxUploadFormInput = pxUploadForm + '-input',
		pxButton = '#' + pxButton,
		pxWidget = '#' + pxWidget,
		buttonClearId = null,
	
		itr = 1, //index/itr of file
		isLimit = (config.limit)? true : false,
		limit = parseInt(config.limit),
	
		e = this, //set e as this
		selector = $(this).selector,
		buttonM = pxButton + ' input, '+ pxButton +' button'; //Accept button as input and as button
		isFile = false, //this is use to hide other inputs in a form
		progress = 0, //percentage of the upload,
		totalForm = 0,
		jqxhr = null, //return object from jquery.ajax,
		timeInterval = config.timeInterval,
		percentageInterval = config.percentageInterval,
		pcount = 0, //progress count to set interval,
		progressTime = null,
		stopUpload = false; //Stop all upload
		
		if (window.FormData) {
			var isHtml5 = true;
		} else {
			var isHtml5 = false;
		}
		
		//Wrap all function that is accessable within the plugin
		var px = {
			
			//Initialize and format data
			init: function(){
				px.form = $(e).parents('form');
				
				//prepend wrapper markup
				px.form.before(wrapper);
				
				//Wrap input button
				$(e).wrap('<div class="px-input-button" />');
				px.form.children('.px-input-button').prepend('<span>'+ config.selectFileLabel +'</span>');
				
				//move upload and clear button into id px_button
				px.form.find(config.buttonUpload + ',' + config.buttonClear).appendTo(pxButton);
				
				//Transform file input into ui button
				px.form.find('.px-input-button').button({
					icons: {
               			primary: "ui-icon-circle-plus"
            		}
				});
				$(config.buttonUpload, pxButton).button({
					icons: {
               			primary: "ui-icon-arrowthickstop-1-n"
            		}
				});
				$(config.buttonClear, pxButton).button({
					icons: {
               			primary: "ui-icon-circle-close"
            		}
				});
				
				//clear all form data
				px.clearFormData(px.form);
				
				px.form.hide();
				this.printForm();
				
				//Disable button
				$(buttonM).attr('disabled','disabled');
			},
			
			//Clone, format and append form
			printForm: function(){
				
				var formId = 'pxupload' + itr,
				iframeId = formId + '_frame';
				
				$('<iframe name="'+ iframeId +'"></iframe>').attr({
					id: iframeId,
					src: 'about:blank',
					style: 'display:none'
				}).prependTo(pxUploadFormInput);
				
				px.form.clone().attr({
					id: formId,
					target: iframeId
				}).prependTo(pxUploadFormInput).show();
				
				//Show only the file input
				px.showInputFile( '#'+formId );
				
				//This is not good but i left no choice because live function is not working on IE
				$(selector).change(function() {
					if (isHtml5) {
						html5Change(this);
					} else {
						uploadChange($(this));
					}
				});
			},
			
			//Show only the file input
			showInputFile: function(form) {
				$(pxUploadFormInput).find(form).children().each(function(){
					isFile = $(this).is(':file');
					if (!isFile && $(this).find(':file').length == 0) {
						$(this).hide();
					}
				});
			},
			//Hide file input and show other data
			hideInputFile: function($form) {
				$form.children().each(function(){
					isFile = $(this).is(':file');
					if (isFile || $(this).find(':file').length > 0) {
						$(this).hide();
					} else {
						$(this).show();
					}
				});
			},
			
			//Validate file
			getFileName: function(file) {
				
				if (file.indexOf('/') > -1){
					file = file.substring(file.lastIndexOf('/') + 1);
				} else if (file.indexOf('\\') > -1){
					file = file.substring(file.lastIndexOf('\\') + 1);
				}
				
				return file;
			},
			
			validateFileName: function(filename) {
				var extensions = new RegExp(config.allowedExtension + '$', 'i');
				if (extensions.test(filename)){
					return filename;
				} else {
					return -1;
				}
			},
			
			getFileSize: function(file) {
				var fileSize = 0;
				if (file.size > 1024 * 1024) {
					fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
				} else {
					fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
				}
				return fileSize;
			},
			
			//clear form data
			clearFormData: function(form) {
				$(form).find(':input').each(function() {
					if (this.type == 'file') {
						$(this).val('');
					}
				});
			}
			
		}
		
		//initialize
		px.init();
		
		/*
		*	Plugin Events/Method
		*/
		
		/*
		* Html5 file change
		*/
		function html5Change($this) {
			$.each( $this.files, function(index, file){
				uploadChange(file);
			});
			
			afterUploadChange();
		}
		
		/*
		*	Html5 Drag and Drop
		*/
		$.event.props.push('dataTransfer');
		$(pxWidget).bind( 'dragenter dragover', false)
		.bind( 'drop', function( e ) {
			e.stopPropagation();
			e.preventDefault();
			
			html5Change(e.dataTransfer);
			
		});
		
		/*
		*	On Change of upload file
		*/
		function uploadChange($this) {
			
			var $form = $(pxUploadFormInput + ' #pxupload'+ itr);
					
			//validate file
			var filename = (isHtml5)? $this.name : px.getFileName( $this.val() );
			if ( px.validateFileName(filename) == -1 ){
				if ($.isFunction(config.onValidationError)) {
					config.onValidationError($this);
				} else {
					alert ('文件格式不对!');
				}
				$form.find(':file').val('');
				return false;
			}
			
			//Limit
			if (limit <= 0) {
				//Your message about exceeding limit
				
				return false;
			}
			limit = limit - 1;
			
			//remove disabled attr
			$(buttonM).removeAttr('disabled');
			
			//remove upload text after uploaded
			$('.upload-data', pxUploadForm).each(function() {
				if ( $(this).find('form').length <= 0 ) {
					$(this).remove();
				}
			});
			
			//append size of the file after filename
			if (isHtml5) {
				filename += ' (' + px.getFileSize($this) + ')';
			}
			
			//DIsplay syled markup				
			$(pxUploadForm).append(
				$('<div>').attr({
					'class': 'upload-data pending ui-widget-content ui-corner-all',
					id: 'pxupload'+ itr +'_text'
				})
				.data('formId', 'pxupload'+ itr)
				.append(' \
					<ul class="actions ui-helper-clearfix"> \
						<li title="Upload" class="upload ui-state-default ui-corner-all"> \
							<span class="ui-icon ui-icon-circle-triangle-e"></span> \
						</li> \
						<li title="Delete" class="delete ui-state-default ui-corner-all"> \
							<span class="ui-icon ui-icon-circle-minus"></span> \
						</li> \
					</ul> \
					<span class="filename">'+ filename +'</span> \
					<div class="progress ui-helper-clearfix"> \
						<div class="progressBar" id="progressBar_'+ itr +'"></div> \
						<div class="percentage">0%</div> \
					</div> \
					<div class="status">等待上传...</div> \
				')
			);
			
			//Store input in form
			$form.data('input', $this);
			
			$form.appendTo(pxUploadForm + ' #pxupload'+ itr +'_text');
			
			//hide the input file
			px.hideInputFile( $form );
			
			//increment for printing form
			itr++;
			
			//print form
			px.printForm();
			
			//Callback on file Changed
			config.onFileChange($this, $form);
			
			if (!isHtml5) {
				afterUploadChange();
			}
		}
		
		/*
		*	After upload change triggers autoupload
		*/
		function afterUploadChange() {
			
			if (config.autoUpload) {
				
				//Display Cancel Button
				toogleCancel(true)
				
				stopUpload = false;
				//Queue and process upload
				uploadQueue();
			}
		}
		
		/*
		*	Queue Upload and send each form to process upload
		*/
		function uploadQueue() {
			
			//stop all upload
			if (stopUpload) {
				return;
			}
			
			totalForm = $(pxUploadForm + ' form').parent('.upload-data').get().length;
			if (totalForm > 0) {
				pendingUpload = $(pxUploadForm + ' form').parent('.upload-data').get(0);
				$form = $(pendingUpload).children('form');
				
				//before upload
				beforeEachUpload( $form );
				
				if (isHtml5) {
					//Upload Using Html5 api
					html5Upload( $form );
				} else {
					
					//upload using iframe
					iframeUpload( $form );
				}
			} else {
				config.afterUpload(pxUploadForm);
				
				//Revert Button to clear
				toogleCancel(false);
			}
		}
		
            /*
            *	Process form Upload
            */
            function html5Upload($form) {
                file = $form.data('input');
                if (file) {
                    var fd = new FormData();
                    fd.append($form.find(selector).attr('name'), file);
                    //get other form input and append to formData
                    $form.find(':input').each(function() {
                        if (this.type != 'file') {
                            fd.append($(this).attr('name'), $(this).val());
                        }
                    });

                    //show progress bar
                    $uploadData = $form.parent();
                    $uploadData.find('.progress').show();
                    $progressBar = $uploadData.find('.progressBar');
                    $percentage = $uploadData.find('.percentage');

                    //Upload using jQuery AJAX
                    jqxhr = $.ajax({
                        url: $form.attr('action'),
                        data: fd,
                        cache: false,
                        contentType: false,
                        processData: false,
                        type: 'POST',
                        xhr: function() {
                            var req = $.ajaxSettings.xhr();
                            if (req) {
                                req.upload.addEventListener('progress',function(ev){
                                    //Display progress Percentage
                                    progress = Math.round(ev.loaded * 100 / ev.total);
                                    $percentage.text(progress.toString() + '%');
                                    $progressBar.progressbar({
                                        value: progress
                                    });
                                }, false);
                            }
                            return req;
                        }
                    })
                    .success(function(data) {
                                var _data=JSON.parse(data);
                                document.getElementById("maingrid").innerHTML="";

                                if(_data.code==200){
                                    alert("上传成功");
                                }else if(_data.code==302){
                                    var table1=document.createElement("table");
                                    table1.border=1;
                                    table1.width="150%";
                                    table1.align="left";
                                    table1.isScroll=true;
                                    table1.draggable=true;
                                    table1.overflowX="scroll";
                                    table1.overflowY="scroll";
                                    var caption= table1.createCaption();
                                    caption.innerHTML="重复数据";
                                    //表头
                                    var tr=table1.insertRow(0);
                                    var td=tr.insertCell(0);
                                    td.innerHTML="报告书编号";

                                    var td=tr.insertCell(1);
                                    td.innerHTML="使用单位地址";

                                    var td=tr.insertCell(2);
                                    td.innerHTML="地址ID";
                                    var td=tr.insertCell(3);
                                    td.innerHTML="组织机构代码";
                                    var td=tr.insertCell(4);
                                    td.innerHTML="使用地点";
                                    var td=tr.insertCell(5);
                                    td.innerHTML="安全管理人员";
                                    var td=tr.insertCell(6);
                                    td.innerHTML="联系电话";
                                    var td=tr.insertCell(7);
                                    td.innerHTML="设备品种";
                                    var td=tr.insertCell(8);
                                    td.innerHTML="单位内部编号";
                                    var td=tr.insertCell(9);
                                    td.innerHTML="制造单位";
                                    var td=tr.insertCell(10);
                                    td.innerHTML="制造许可编号";
                                    var td=tr.insertCell(11);
                                    td.innerHTML="制造日期";
                                    var td=tr.insertCell(12);
                                    td.innerHTML="规格型号";
                                    var td=tr.insertCell(13);
                                    td.innerHTML="产品编号";
                                    var td=tr.insertCell(14);
                                    td.innerHTML="工作级别";
                                    var td=tr.insertCell(15);
                                    td.innerHTML="经度";
                                    var td=tr.insertCell(16);
                                    td.innerHTML="纬度";
                                    var td=tr.insertCell(17);
                                    td.innerHTML="单个起重机的图片路径";
                                    var td=tr.insertCell(18);
                                    td.innerHTML="每种类型起重器的图片路径";
                                    var td=tr.insertCell(19);
                                    td.innerHTML="风险值";
                                    var td=tr.insertCell(20);
                                    td.innerHTML="额定起重量";


                                    for(var i=0;i<_data.data.length;i++){

                                        var j=i+1;
                                        var tr=table1.insertRow(j);
                                        var td=tr.insertCell(0);
                                        td.innerHTML=_data.data[i].reportNumber;

                                        var td=tr.insertCell(1);
                                        td.innerHTML=_data.data[i].unitAddress;

                                        var td=tr.insertCell(2);
                                        td.innerHTML=_data.data[i].addressId;
                                        var td=tr.insertCell(3);
                                        td.innerHTML=_data.data[i].organizeCode;
                                        var td=tr.insertCell(4);
                                        td.innerHTML=_data.data[i].userPoint;
                                        var td=tr.insertCell(5);
                                        td.innerHTML=_data.data[i].safeManager;
                                        var td=tr.insertCell(6);
                                        td.innerHTML=_data.data[i].contactPhone;
                                        var td=tr.insertCell(7);
                                        td.innerHTML=_data.data[i].equipmentVariety;
                                        var td=tr.insertCell(8);
                                        td.innerHTML=_data.data[i].unitNumber;
                                        var td=tr.insertCell(9);
                                        td.innerHTML=_data.data[i].manufactureUnit;
                                        var td=tr.insertCell(10);
                                        td.innerHTML=_data.data[i].manufactureLicenseNumber;
                                        var td=tr.insertCell(11);
                                        td.innerHTML=_data.data[i].manufactureDate;
                                        var td=tr.insertCell(12);
                                        td.innerHTML=_data.data[i].specification;
                                        var td=tr.insertCell(13);
                                        td.innerHTML=_data.data[i].pNumber;
                                        var td=tr.insertCell(14);
                                        td.innerHTML=_data.data[i].workLevel;
                                        var td=tr.insertCell(15);
                                        td.innerHTML=_data.data[i].lng;
                                        var td=tr.insertCell(16);
                                        td.innerHTML=_data.data[i].lat;
                                        var td=tr.insertCell(17);
                                        td.innerHTML=_data.data[i].singlePicURL;
                                        var td=tr.insertCell(18);
                                        td.innerHTML=_data.data[i].typePicURL;
                                        var td=tr.insertCell(19);
                                        td.innerHTML=_data.data[i].riskValue;
                                        var td=tr.insertCell(20);
                                        td.innerHTML=_data.data[i].ratedLiftWeight;

                                    }
                                    document.getElementById("maingrid").appendChild(table1);
                                    if(window.confirm("是否覆盖重复数据？")){
                                        $.post($.URL.craneinspectreport.addRepeat,null,null,"json");
                                    }

                                }else if(_data.code==505){
                                    var table1=document.createElement("table");
                                    table1.border=1;
                                    table1.width="100%";
                                    table1.align="left";
                                    table1.isScroll=true;
                                    table1.draggable=true;
                                    table1.overflowX="scroll";
                                    table1.overflowY="scroll";
                                    var caption= table1.createCaption();
                                    caption.innerHTML="以下类型在起重机类别中不存在,请补全后再上传  <a href='craneInspectReport/addCraneType.html'>前往输入</a>";
                                    //表头
                                    var tr=table1.insertRow(0);
                                    var td=tr.insertCell(0);
                                    td.innerHTML="起重机类型";

                                    for(var i=0;i<_data.data.length;i++){

                                        var j=i+1;
                                        var tr=table1.insertRow(j);
                                        var td=tr.insertCell(0);
                                        td.innerHTML=_data.data[i];
                                    }
                                    document.getElementById("maingrid").appendChild(table1);
                                }
                        afterEachUpload($form.attr('id'), data );
                    })
                    .error(function(jqXHR, textStatus, errorThrown) {
                        afterEachUpload($form.attr('id'), null, textStatus, errorThrown );
                    })
                    .complete(function(jqXHR, textStatus) {
                        $progressBar.progressbar({
                            value: 100
                        });
                        $percentage.text('100%');

                        uploadQueue();
                    });
                }

                $form.remove();
            }

		/*
		*	Iframe Upload Process
		*/
		function iframeUpload($form) {
			
			//show progress bar
			$uploadData = $form.parent();
			$uploadData.find('.progress').show();
			$percentage = $uploadData.find('.percentage');
			$progressBar = $uploadData.find('.progressBar');
			
			pcount = 0;
			dummyProgress($progressBar, $percentage);
			
			$form.submit();
			
			var id = pxWidget + ' #' + $form.attr('id');
			$(id +'_frame').load(function(){
				
				data = $(this).contents().find('body').html();
				
				afterEachUpload($form.attr('id'), data);
				
				clearTimeout ( progressTime );
				progress = 100;
				$percentage.text(progress.toString() + '%');
				$progressBar.progressbar({
					value: progress
				});
				
				uploadQueue();
				
			});
		}
		
		/*
		*	Show the progress bar to the user
		*/
		function dummyProgress($progressBar, $percentage) {
			
			if (percentageInterval[pcount]) {
				progress = percentageInterval[pcount] + Math.floor( Math.random() * 5 + 1 );
				$percentage.text(progress.toString() + '%');
				$progressBar.progressbar({
					value: progress
				});
			}
			
			if (timeInterval[pcount]) {
				progressTime = setTimeout(function(){
					dummyProgress($progressBar, $percentage)
				}, timeInterval[pcount] * 1000);
			}
			
			pcount++;
		}
		
		/*
		*	before Upload
		*/
		function beforeAllUpload() {
			//trigger before upload callback
			$continue = config.beforeUpload(e, pxButton);
			if ($continue === false) {			
				return false;
			}
			
			//Show Cancle Button
			toogleCancel(true);
			
			//process and queue upload
			uploadQueue();
		}
		
		/*
		* Before Each file is uploaded
		*/
		function beforeEachUpload($form) {
			
			//trigger before upload callback
			config.beforeEachUpload($form);
			
			$uploadData = $form.parent();
			$uploadData.find('.status').text('上传中...');
			$uploadData.removeClass('pending').addClass('uploading');
			$uploadData.find('.delete').removeClass('delete').addClass('cancel').attr('title', 'Cancel');
		}
		
		/*
		* After Each file is uploaded
		*/
		function afterEachUpload(formId, data, status, errorThrown) {
			
			if (data) {
               var x="<div id='status'>success</div><div id='message'>上传成功</div>";
              //data = $('<div>').append(data);
               data = $('<div>').append(x);
				status = $(data).find('#status').text();
			}
			
			formId = pxWidget + ' #' + formId;
			$uploadData = $(formId + '_text');
			
			if (status == 'success'){
				
				$uploadData.removeClass('uploading').addClass('success');
				$uploadData.children('.status').html( $(data).find('#message').text() );
				
			} else if (status == 'error'){
				
				$uploadData.removeClass('uploading').addClass('error');
				
				//if client side error other display error from backend
				if (errorThrown) {
					$uploadData.children('.status').html( errorThrown );
				} else {
					$uploadData.children('.status').html( $(data).find('#message').text() );
				}
				
			} else if (status == 'abort') {
				
				$uploadData.removeClass('uploading').addClass('cancel');
				
				$uploadData.children('.status').html( 'Cancelled' );
			}
			
			$uploadData.find('.cancel').removeClass('cancel').addClass('delete').attr('title', 'Delete');
			
			//hide progress bar
			$uploadData.find('.progress').hide();
			
			//trigger after each upload
			config.afterEachUpload(data, status, $uploadData);
			
			$(formId).remove();
			$(formId + '_frame').remove();
		}
		
		/*
		*	Toggle Cancel/Delete button
		*/
		function toogleCancel(cancel) {
			
			if (cancel) {
				//store button clear id
				buttonClearId = $(config.buttonClear, pxButton).attr('id');
				//Cancel Button
				$(config.buttonClear, pxButton).attr({ id: 'px-cancel', title: 'Cancel' });
			} else {
				//Clear button
				$('#px-cancel', pxButton).attr({ id: buttonClearId, title: 'Clear' });
			}
		}
		
		/*
		*	Onlick submit button: start upload
		*/
		$(config.buttonUpload, pxButton).click(function(){
			
			stopUpload = false;
			
			beforeAllUpload();
		});
		
		/*
		* Individual Upload
		*/
		$('.upload', pxUploadForm).live('click', function(){
			
			$form = $(this).parents('.upload-data').children('form');
			if ($form.length > 0) {
				
				//Show Cancle Button
				toogleCancel(true);
								
				//before upload
				beforeEachUpload( $form );
				
				if (isHtml5) {
					//Upload Using Html5 api
					html5Upload( $form );
				} else {
					
					//upload using iframe
					iframeUpload( $form );
				}
				
				stopUpload = true;
			}
		});
		
		//Button Clear Event
		$(config.buttonClear, pxButton).live('click', function(){
			$(pxUploadForm).fadeOut('slow',function(){
				$(this).empty();
				$(this).show();
				$(pxUploadFormInput).empty();
				
				itr = 1; //reset iteration
				limit = parseInt(config.limit);
				
				//print the First form
				px.printForm();
				
				//disable button
				$(buttonM).attr('disabled','disabled');
			});
		});
		
		$('.delete', pxUploadForm).live('click', function(){
			
			limit++;
			
			var id = pxWidget + ' #' + $(this).parents('.upload-data').data('formId');
			$(id+'_text').fadeOut('slow',function(){
				$(id+'_frame').remove();
				$(this).remove();
				
				//disable button
				if ($(pxUploadForm).find('form').length <= 1) {
					$(buttonM).attr('disabled','disabled');	
				}
			});
			
			//on file remove callback
			config.onFileRemove(this);
		});
		
		/*
		*	Cancel individual upload
		*/
		$('.cancel', pxUploadForm).live('click', function() {
			if (jqxhr) {
				jqxhr.abort();
			}
			
			if (!isHtml5) {
				$form = $(this).parents('.upload-data').children('form');
				$form.remove();
				afterEachUpload($form.attr('id'), null, 'abort', 'Cancelled');
			}
		});
		
		/*
		*	Cancel all uploads
		*/
		$('#px-cancel', pxButton).live('click', function(){
			stopUpload = true;
			if (jqxhr) {
				jqxhr.abort();
			}
			
			$('form', pxUploadForm).each(function(){
				afterEachUpload($(this).attr('id'), null, 'abort', 'Cancelled');
			});
			
			//Show Clear Button
			toogleCancel(false);
		});
		
		/* Icons hover */
		$(".px-widget .actions li").live("mouseover mouseout", function(event) {
			if ( event.type == "mouseover" ) {
				$(this).addClass('ui-state-hover');
			} else {
				$(this).removeClass("ui-state-hover");
			}
		});
		
		return this;
	}
})(jQuery);